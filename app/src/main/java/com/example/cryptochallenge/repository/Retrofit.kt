package com.example.cryptochallenge.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    var webService: ApiService = build()

    private fun build(): ApiService = Retrofit.Builder()
        .baseUrl("https://api.bitso.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}