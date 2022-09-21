package com.example.cryptochallenge.repository

import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import retrofit2.Response

interface CryptoRepositoryInterface {
    suspend fun getDetailCripto(): Response<TickerResponse>
    suspend fun getAllCriptoCoins(): Response<AvailableBooksResponse>
    suspend fun getOpenOrders(): Response<OrderBookResponse>
}