package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.domain.response.TickerResponse
import com.example.cryptochallenge.repository.CryptoRepositoryInterface
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.utils.toDatabase
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepositoryInterface) {

    fun getCryptoDetail(book: String, ticker: (Observable<TickerResponse>) -> Unit) {
        try {
            //TODO Use RxJava with Room
            val response = repository.getCryptoDetailFromApi(book)
            ticker.invoke(response)
        } catch (e: HttpException) { }
        catch (e: IOException) { }
    }

    fun getAllCryptoCoins(): Flow<RequestState<List<CryptoCoins>>> = channelFlow {
        var cryptoCoins: List<CryptoCoins> = arrayListOf()
        try {
            send(RequestState.Loading())
            val response = repository.getAllCryptoCoinsFromApi()
            cryptoCoins = if(response.isNotEmpty()){
                repository.deleteCryptoCoinsFromDatabase()
                repository.insertCryptoCoinsToDatabase(response.map { it.toDatabase() })
                response
            } else{
                repository.getAllCryptoCoinsFromDatabase()
            }
            send(RequestState.Success(cryptoCoins))
        } catch (e: HttpException) {
            send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            if(cryptoCoins.isEmpty()){
                send(RequestState.Error("Couldn't reach server. Check your internet connection."))
            } else {
                cryptoCoins = repository.getAllCryptoCoinsFromDatabase()
                send(RequestState.Success(cryptoCoins))
            }
        }
    }

    fun getOpenOrders(book: String): Flow<RequestState<CryptoData>> = channelFlow {
        var bidsAsks: CryptoData
        try {
            send(RequestState.Loading())
            val response = repository.getBidsAsksFromApi(book)
            bidsAsks = run {
                repository.deleteBidsAsksFromDatabase(book)
                response.bidsAsks?.map { it.toDatabase() }?.let { repository.insertBidsAsksToDatabase(it) }
                response
            }
            send(RequestState.Success(bidsAsks))
        } catch (e: HttpException) {
            send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            bidsAsks = repository.getBidsAsksFromDatabase(book)
            send(RequestState.Success(bidsAsks))
        }
    }
}