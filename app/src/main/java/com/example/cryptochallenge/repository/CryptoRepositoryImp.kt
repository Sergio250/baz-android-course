package com.example.cryptochallenge.repository

import com.example.cryptochallenge.data.database.dao.CryptoDaoInterface
import com.example.cryptochallenge.data.database.entities.CryptoAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoBidsEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity
import com.example.cryptochallenge.domain.base.CryptoBook
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import com.example.cryptochallenge.utils.toDomain
import retrofit2.Response
import javax.inject.Inject

class CryptoRepositoryImp @Inject constructor(private val api: ApiServiceInterface, private val dao: CryptoDaoInterface) :
    CryptoRepositoryInterface {

    override suspend fun getAllCryptoCoinsFromApi(): List<CryptoCoins> {
        val response: Response<AvailableBooksResponse> = api.getAllCryptoCoins()
        return response.body()?.payload?.map { it.toDomain() } ?: arrayListOf()
    }
    override suspend fun getAllCryptoCoinsFromDatabase(): List<CryptoCoins>{
        val response = dao.getAllCryptoCoinsFromDatabase()
        return response.map { it.toDomain() }
    }
    override suspend fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>) = dao.insertCryptoCoinsToDatabase(cryptoCoins)
    override suspend fun deleteCryptoCoinsFromDatabase() = dao.deleteCryptoCoinsFromDatabase()



    override suspend fun getDetailCryptoFromApi(book: String): CryptoDetail {
        val response: Response<TickerResponse> = api.getDetailCrypto(book)
        return response.body()?.payload?.toDomain() ?: CryptoDetail()
    }
    override suspend fun getDetailCryptoFromDatabase(book: String): CryptoDetail {
        val response = dao.getDetailCryptoCoinFromDatabase(book)
        return response.toDomain()
    }
    override suspend fun insetCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity) = dao.insertCryptoDetailToDatabase(cryptoCoin)
    override suspend fun deleteCryptoDetailFromDatabase(book: String) = dao.deleteCryptoDetailFromDatabase(book)



    override suspend fun getBidsAsksFromApi(book: String): CryptoBook{
        val response: Response<OrderBookResponse> = api.getOpenOrders(book)
        return response.body()?.payload?.toDomain() ?: CryptoBook()
    }
    override suspend fun getBidsAsksFromDatabase(book: String) = dao.getBidsAndAsksCryptoCoinFromDatabase(book)
    override suspend fun insertBidsAsksToDatabase(cryptoBidsCoin: List<CryptoBidsEntity>, cryptoAsksCoin: List<CryptoAsksEntity>) =
        dao.insertBidsAndAsksCryptoCoinToDatabase(cryptoBidsCoin, cryptoAsksCoin)
    override suspend fun deleteBidsAsksFromDatabase(book: String) = dao.deleteBidsAndAsksCryptoCoinFromDatabase(book)


}
