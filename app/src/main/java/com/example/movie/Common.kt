package com.example.movie

import com.example.movie.retrofit.RetrofitClient

object Common {
    private val BASE_URL =
        "https://api.nytimes.com/svc/movies/v2/reviews/"
//?api-key=gvQKO6QDtVxq2DAVz1euMcRM5TXRgsYh
    val retrofitService: RetrofitServices
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}