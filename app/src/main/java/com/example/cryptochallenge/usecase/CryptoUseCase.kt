package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.base.CryptoBook
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.repository.CryptoRepositoryInterface
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.utils.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepositoryInterface) {

    fun getDetailCrypto(book: String): Flow<RequestState<CryptoDetail>> = channelFlow {
        var cryptoDetail: CryptoDetail
        try {
            send(RequestState.Loading())
            val response = repository.getDetailCryptoFromApi(book)
            cryptoDetail = run {
                repository.deleteCryptoDetailFromDatabase(book)
                repository.insetCryptoDetailToDatabase(response.toDatabase())
                response
            }
            send(RequestState.Success(cryptoDetail))
        } catch (e: HttpException) {
            send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            cryptoDetail = repository.getDetailCryptoFromDatabase(book)
            send(RequestState.Success(cryptoDetail))
        }
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

    fun getOpenOrders(book: String): Flow<RequestState<CryptoBook>> = channelFlow {
        var bidsAsks = CryptoBook()
        try {
            send(RequestState.Loading())
            val response = repository.getBidsAsksFromApi(book)
            bidsAsks = run {
                repository.deleteBidsAsksFromDatabase(book)
                repository.insertBidsAsksToDatabase(response.bids?.map { it.toDatabase()} ?: arrayListOf(),
                    response.asks?.map { it.toDatabase() } ?: arrayListOf())
                response
            }
            send(RequestState.Success(bidsAsks))
        } catch (e: HttpException) {
            send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            if(bidsAsks.asks?.isEmpty() == true){
                send(RequestState.Error("Couldn't reach server. Check your internet connection."))
            } else {
                bidsAsks = repository.getBidsAsksFromDatabase(book)
                send(RequestState.Success(bidsAsks))
            }
        }
    }

}