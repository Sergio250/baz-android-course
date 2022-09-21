package com.example.cryptochallenge.repository

import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("v3/ticker/?book=btc_mxn")
    suspend fun getDetailCripto(
        @Query("btc_mxn") book: String
    ): Response<TickerResponse>

    @GET("v3/available_books/")
    suspend fun getAllCriptoCoins( ): Response<AvailableBooksResponse>

    @GET("v3/order_book/?book=btc_mxn")
    suspend fun getOpenOrders( ): Response<OrderBookResponse>

}