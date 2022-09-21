package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.repository.CryptoRepositoryInterface
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.domain.response.TickerResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepositoryInterface) {

    fun getDetailCripto(): Flow<RequestState<TickerResponse>> = channelFlow {
        try {
            send(RequestState.Loading<TickerResponse>())
            val response = repository.getDetailCripto()
            val ticker: TickerResponse = if (response.code() in 200..299) {
                response.body() as TickerResponse
            } else {
                TickerResponse()
            }
            send(RequestState.Success<TickerResponse>(ticker))
        } catch (e: HttpException) {
            send(RequestState.Error<TickerResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            send(RequestState.Error<TickerResponse>("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getAllCriptoCoins(): Flow<RequestState<AvailableBooksResponse>> = channelFlow {
        try {
            send(RequestState.Loading<AvailableBooksResponse>())
            val response = repository.getAllCriptoCoins()
            val criptoCoins: AvailableBooksResponse = if (response.code() in 200..299) {
                response.body() as AvailableBooksResponse
            } else {
                AvailableBooksResponse()
            }
            send(RequestState.Success<AvailableBooksResponse>(criptoCoins))
        } catch (e: HttpException) {
            send(RequestState.Error<AvailableBooksResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            send(RequestState.Error<AvailableBooksResponse>("Couldn't reach server. Check your internet connection."))
        }
    }

    fun getOpenOrders(): Flow<RequestState<OrderBookResponse>> = channelFlow {
        try {
            send(RequestState.Loading<OrderBookResponse>())
            val response = repository.getOpenOrders()
            val criptoCoins: OrderBookResponse = if (response.code() in 200..299) {
                response.body() as OrderBookResponse
            } else {
                OrderBookResponse()
            }
            send(RequestState.Success<OrderBookResponse>(criptoCoins))
        } catch (e: HttpException) {
            send(RequestState.Error<OrderBookResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            send(RequestState.Error<OrderBookResponse>("Couldn't reach server. Check your internet connection."))
        }
    }

}