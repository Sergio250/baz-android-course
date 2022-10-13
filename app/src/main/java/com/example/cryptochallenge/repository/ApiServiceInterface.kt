package com.example.cryptochallenge.repository

import com.example.cryptochallenge.network.models.AvailableBooksResponse
import com.example.cryptochallenge.network.models.OrderBookResponse
import com.example.cryptochallenge.network.models.TickerResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("available_books/")
    suspend fun getAllCryptoCoins(): Response<AvailableBooksResponse>

    @GET("order_book/")
    suspend fun getOpenOrders(
        @Query("book") book: String,
    ): Response<OrderBookResponse>

    @GET("ticker/")
    fun getCryptoDetail(
        @Query("book") book: String
    ): Observable<TickerResponse>
}
