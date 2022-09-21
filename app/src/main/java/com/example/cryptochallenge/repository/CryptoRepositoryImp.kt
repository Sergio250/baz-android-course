package com.example.cryptochallenge.repository

import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import retrofit2.Response
import javax.inject.Inject

class CryptoRepositoryImp @Inject constructor(private val api: ApiService) :
    CryptoRepositoryInterface {
    override suspend fun getDetailCripto(): Response<TickerResponse> {
        return api.getDetailCripto("")
    }

    override suspend fun getAllCriptoCoins(): Response<AvailableBooksResponse> {
        return api.getAllCriptoCoins()
    }

    override suspend fun getOpenOrders(): Response<OrderBookResponse> {
        return api.getOpenOrders()
    }
}
