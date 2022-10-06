package com.example.cryptochallenge.repository

import com.example.cryptochallenge.data.database.dao.CryptoDaoInterface
import com.example.cryptochallenge.data.database.entities.*
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import com.example.cryptochallenge.utils.toDomain
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class CryptoRepositoryImp @Inject constructor(private val api: ApiServiceInterface, private val dao: CryptoDaoInterface) :
    CryptoRepositoryInterface {

    override suspend fun getAllCryptoCoinsFromApi(): List<CryptoCoins> {
        val response: Response<AvailableBooksResponse> = api.getAllCryptoCoins()
        return response.body()?.payload?.map { it.toDomain() } ?: arrayListOf()
    }
    override suspend fun getAllCryptoCoinsFromDatabase(): List<CryptoCoins> {
        val response = dao.getAllCryptoCoinsFromDatabase()
        return response.map { it.toDomain() }
    }
    override suspend fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>) = dao.insertCryptoCoinsToDatabase(cryptoCoins)
    override suspend fun deleteCryptoCoinsFromDatabase() = dao.deleteCryptoCoinsFromDatabase()

    override fun getCryptoDetailFromApi(book: String): Observable<TickerResponse> {
        return api.getCryptoDetail(book)
    }
    override fun getCryptoDetailFromDatabase(book: String): CryptoDetail {
        val response = dao.getCryptoDetailFromDatabase(book)
        return response.toDomain()
    }
    override fun insertCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity) = dao.insertCryptoDetailToDatabase(cryptoCoin)
    override fun deleteCryptoDetailFromDatabase(book: String) = dao.deleteCryptoDetailFromDatabase(book)

    override suspend fun getBidsAsksFromApi(book: String): CryptoData {
        val response: Response<OrderBookResponse> = api.getOpenOrders(book)
        return response.body()?.payload?.toDomain() ?: CryptoData()
    }
    override suspend fun getBidsAsksFromDatabase(book: String): CryptoData {
        val response = dao.getBidsAsksCryptoCoinFromDatabase(book)
        return response.map { it.toDomain() }.toDomain()
    }
    override suspend fun insertBidsAsksToDatabase(cryptoBidsAsksCoins: List<CryptoBidsAsksEntity>) =
        dao.insertBidsAndAsksCryptoCoinToDatabase(cryptoBidsAsksCoins)
    override suspend fun deleteBidsAsksFromDatabase(book: String) = dao.deleteBidsAndAsksCryptoCoinFromDatabase(book)
}
