package com.example.cryptochallenge.repository

import com.example.cryptochallenge.data.database.entities.*
import com.example.cryptochallenge.domain.base.CryptoBook
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoDetail

interface CryptoRepositoryInterface {

    suspend fun getAllCryptoCoinsFromApi(): List<CryptoCoins>
    suspend fun getAllCryptoCoinsFromDatabase(): List<CryptoCoins>
    suspend fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>)
    suspend fun deleteCryptoCoinsFromDatabase()

    suspend fun getDetailCryptoFromApi(book: String): CryptoDetail
    suspend fun getDetailCryptoFromDatabase(book: String): CryptoDetail
    suspend fun insetCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity)
    suspend fun deleteCryptoDetailFromDatabase(book: String)

    suspend fun getBidsAsksFromApi(book: String): CryptoBook
    suspend fun getBidsAsksFromDatabase(book: String) : CryptoBook
    suspend fun insertBidsAsksToDatabase(cryptoBidsCoin: List<CryptoBidsEntity>, cryptoAsksCoin: List<CryptoAsksEntity>)
    suspend fun deleteBidsAsksFromDatabase(book: String)


}