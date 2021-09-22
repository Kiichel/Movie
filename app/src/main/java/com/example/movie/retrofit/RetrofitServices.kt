package com.example.movie.retrofit

import com.example.movie.model.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("all.json")
    fun getMovieList(@Query("api-key") apiKey: String, @Query("offset") offset: Int) : Call<Root>
}
