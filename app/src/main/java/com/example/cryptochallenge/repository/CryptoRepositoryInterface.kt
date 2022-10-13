package com.example.cryptochallenge.repository

import com.example.cryptochallenge.data.database.entities.CryptoBidsAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.network.models.TickerResponse
import io.reactivex.Observable

interface CryptoRepositoryInterface {

    suspend fun getAllCryptoCoinsFromApi(): List<CryptoCoins>
    suspend fun getAllCryptoCoinsFromDatabase(): List<CryptoCoins>
    suspend fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>)
    suspend fun deleteCryptoCoinsFromDatabase()

    fun getCryptoDetailFromApi(book: String): Observable<TickerResponse>
    fun getCryptoDetailFromDatabase(book: String): CryptoDetail
    fun insertCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity)
    fun deleteCryptoDetailFromDatabase(book: String)

    suspend fun getBidsAsksFromApi(book: String): CryptoData
    suspend fun getBidsAsksFromDatabase(book: String): CryptoData
    suspend fun insertBidsAsksToDatabase(cryptoBidsAsksCoins: List<CryptoBidsAsksEntity>)
    suspend fun deleteBidsAsksFromDatabase(book: String)
}
