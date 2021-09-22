package com.example.movie

import com.example.movie.model.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("all.json")
    fun getMovieList(@Query("api-key") apiKey: String): Call<Root>
}
