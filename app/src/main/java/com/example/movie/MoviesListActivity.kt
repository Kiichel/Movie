package com.example.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.Movie
import com.example.movie.model.Root
import com.example.movie.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity() {
    private lateinit var mService: RetrofitServices
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private var ctx: Context = this

    private val apiKey = "gvQKO6QDtVxq2DAVz1euMcRM5TXRgsYh"
    private var offset = 0
    private var hasMore = false
    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        mService = Common.retrofitService

        recyclerView = findViewById(R.id.recyclerViewMoviesId)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerViewAdapter(baseContext, movies)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter

        setRecyclerViewScrollListener()
        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList(apiKey, offset).enqueue(object : Callback<Root> {
            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(ctx, "error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                val root = response.body()
                val results = root?.results

                if(root?.num_results == null) {
                    return
                }

                if (results != null) {
                    for (result in results) {
                        var movieTemp = Movie()
                        movieTemp.title = result.display_title.toString()
                        movieTemp.description = result.summary_short.toString()
                        movieTemp.image = result.multimedia?.src.toString()

                        movies.add(movieTemp)
                    }
                }

                offset += root?.num_results!!

                hasMore = root.has_more
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setRecyclerViewScrollListener() {
        var scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                if (recyclerView != null) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                if (offset == lastVisibleItemPosition + 1 && hasMore) {
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        getAllMovieList()
                    }, 100)
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }
}