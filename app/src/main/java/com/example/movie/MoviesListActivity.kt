package com.example.movie

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.Movie
import com.example.movie.model.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerViewAdapter
    lateinit private var recyclerView: RecyclerView
    private var ctx: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        mService = Common.retrofitService
        recyclerView = findViewById(R.id.recyclerViewMoviesId)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList("gvQKO6QDtVxq2DAVz1euMcRM5TXRgsYh").enqueue(object : Callback<Root> {
            override fun onFailure(call: Call<Root>, t: Throwable) {
                Toast.makeText(ctx,"error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                val root = response.body()
                val results = root?.results
                var movies: MutableList<Movie> = mutableListOf()

                if (results != null) {
                    for(result in results){
                        var movieTemp = Movie()
                        movieTemp.title = result.display_title.toString()
                        movieTemp.description = result.summary_short.toString()
                        movieTemp.image = result.multimedia?.src.toString()

                        movies.add(movieTemp)
                    }
                }

                adapter = RecyclerViewAdapter(baseContext, movies)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        })
    }
}