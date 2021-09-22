package com.example.movie

import com.example.movie.retrofit.RetrofitClient
import com.example.movie.retrofit.RetrofitServices

object Common {
    private val BASE_URL =
        "https://api.nytimes.com/svc/movies/v2/reviews/"

    val retrofitService: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}