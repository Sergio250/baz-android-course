package com.example.cryptochallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptochallenge.data.database.entities.*

@Dao
interface CryptoDaoInterface {

    @Query("SELECT * FROM crypto_coins")
    fun getAllCryptoCoinsFromDatabase(): List<CryptoCoinsEntity>
    @Insert
    fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>)
    @Query("DELETE FROM crypto_coins")
    fun deleteCryptoCoinsFromDatabase()

    @Query("SELECT * FROM crypto_detail WHERE book LIKE :book")
    fun getCryptoDetailFromDatabase(book: String): CryptoDetailEntity
    @Insert
    fun insertCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity)
    @Query("DELETE FROM crypto_detail WHERE book LIKE :book")
    fun deleteCryptoDetailFromDatabase(book: String)

    @Query("SELECT * FROM crypto_bids_asks WHERE book LIKE :book")
    fun getBidsAsksCryptoCoinFromDatabase(book: String): List<CryptoBidsAsksEntity>
    @Insert
    fun insertBidsAndAsksCryptoCoinToDatabase(cryptoBidsAsksCoin: List<CryptoBidsAsksEntity>)
    @Query("DELETE FROM crypto_bids_asks WHERE book LIKE :book")
    fun deleteBidsAndAsksCryptoCoinFromDatabase(book: String)
}
