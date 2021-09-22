package com.example.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.model.Movie

class RecyclerViewAdapter(private val context: Context, _movies: List<Movie>) : RecyclerView.Adapter<RecyclerViewAdapter.MoviesHolder>() {
    private var movies = _movies

    class MoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageFilm: ImageView? = null
        var nameFilm: TextView? = null
        var descName: TextView? = null

        init {
            imageFilm = itemView.findViewById(R.id.imageFilmId)
            nameFilm = itemView.findViewById(R.id.nameFilmId)
            descName = itemView.findViewById(R.id.descFilmId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_movie, parent, false)
        return MoviesHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        holder.imageFilm?.let {
            Glide
                .with(it.context)
                .load(movies[position].image)
                .into(it)
        }
        holder.nameFilm?.text = movies[position].title
        holder.descName?.text = movies[position].description
    }


    override fun getItemCount() = movies.size
}