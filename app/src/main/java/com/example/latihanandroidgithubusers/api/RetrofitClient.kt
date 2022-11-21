package com.example.latihanandroidgithubusers.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/* This is retrofit singleton */
object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance: Api by lazy {
        retrofit.create(Api::class.java)
    }
}