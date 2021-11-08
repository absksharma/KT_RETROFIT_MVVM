package com.example.kt_retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: BlogApi by lazy {
        retrofit.create(BlogApi::class.java)
    }

}