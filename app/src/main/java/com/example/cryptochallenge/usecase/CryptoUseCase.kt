package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.repository.CryptoRepositoryInterface
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.utils.toDatabase
import com.example.cryptochallenge.utils.toDomain
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepositoryInterface) {

    fun getCryptoDetail(book: String): Observable<CryptoDetail> = Observable.create { emitter ->
        try {
            val cryptoDetail: CryptoDetail
            val response = repository.getCryptoDetailFromApi(book)
            cryptoDetail = run {
                repository.deleteCryptoDetailFromDatabase(book)
                repository.insertCryptoDetailToDatabase(response.map { it.payload?.toDatabase() }.blockingFirst()!!)
                response.map { it.payload?.toDomain() }.blockingFirst() ?: CryptoDetail()
            }
            emitter.onNext(cryptoDetail)
        } catch (e: HttpException) {
            emitter.onError(Throwable(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emitter.onNext(repository.getCryptoDetailFromDatabase(book))
        }
    }

    fun getAllCryptoCoins(): Flow<RequestState<List<CryptoCoins>>> = channelFlow {
        withContext(IO) {
            try {
                val cryptoCoins: List<CryptoCoins>
                send(RequestState.Loading())
                val response = repository.getAllCryptoCoinsFromApi()
                cryptoCoins = response.let {
                    repository.deleteCryptoCoinsFromDatabase()
                    repository.insertCryptoCoinsToDatabase(response.map { it.toDatabase() })
                    response
                }
                send(RequestState.Success(cryptoCoins))
            } catch (e: HttpException) {
                send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                send(RequestState.Success(repository.getAllCryptoCoinsFromDatabase()))
            }
        }
    }

    fun getOpenOrders(book: String): Flow<RequestState<CryptoData>> = channelFlow {
        withContext(IO) {
            try {
                val bidsAsks: CryptoData
                send(RequestState.Loading())
                val response = repository.getBidsAsksFromApi(book)
                bidsAsks = run {
                    repository.deleteBidsAsksFromDatabase(book)
                    response.bidsAsks?.map { it.toDatabase() }
                        ?.let { repository.insertBidsAsksToDatabase(it) }
                    response
                }
                send(RequestState.Success(bidsAsks))
            } catch (e: HttpException) {
                send(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                send(RequestState.Success(repository.getBidsAsksFromDatabase(book)))
            }
        }
    }
}
