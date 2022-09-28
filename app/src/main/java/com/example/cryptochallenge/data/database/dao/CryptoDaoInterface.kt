package com.example.cryptochallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.cryptochallenge.data.database.entities.CryptoAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoBidsEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity
import com.example.cryptochallenge.domain.base.CryptoBook
import com.example.cryptochallenge.utils.toDomain

@Dao
interface CryptoDaoInterface {

    @Query("SELECT * FROM crypto_coins")
    fun getAllCryptoCoinsFromDatabase(): List<CryptoCoinsEntity>
    @Insert
    fun insertCryptoCoinsToDatabase(cryptoCoins: List<CryptoCoinsEntity>)
    @Query("DELETE FROM crypto_coins")
    fun deleteCryptoCoinsFromDatabase()


    @Query("SELECT * FROM crypto_detail WHERE book LIKE :book")
    fun getDetailCryptoCoinFromDatabase(book: String): CryptoDetailEntity
    @Insert
    fun insertCryptoDetailToDatabase(cryptoCoin: CryptoDetailEntity)
    @Query("DELETE FROM crypto_detail WHERE book LIKE :book")
    fun deleteCryptoDetailFromDatabase(book: String)


    @Query("SELECT * FROM crypto_bids WHERE book LIKE :book")
    fun getBidsCryptoCoinFromDatabase(book: String): List<CryptoBidsEntity>
    @Query("SELECT * FROM crypto_asks WHERE book LIKE :book")
    fun getAsksCryptoCoinFromDatabase(book: String): List<CryptoAsksEntity>
    @Transaction
    fun getBidsAndAsksCryptoCoinFromDatabase(book: String): CryptoBook {
        val bids = getBidsCryptoCoinFromDatabase(book)
        val asks = getAsksCryptoCoinFromDatabase(book)
        return CryptoBook(asks = asks.map { it.toDomain() }, bids = bids.map { it.toDomain() }, "", "")
    }


    @Insert
    fun insertBidsCryptoCoinToDatabase(cryptoBidsCoin: List<CryptoBidsEntity>)
    @Insert
    fun insertAsksCryptoCoinToDatabase(cryptoAskCoin: List<CryptoAsksEntity>)
    @Transaction
    fun insertBidsAndAsksCryptoCoinToDatabase(cryptoBidsCoin: List<CryptoBidsEntity>, cryptoAskCoin: List<CryptoAsksEntity>){
        insertAsksCryptoCoinToDatabase(cryptoAskCoin)
        insertBidsCryptoCoinToDatabase(cryptoBidsCoin)
    }

    @Query("DELETE FROM crypto_bids WHERE book LIKE :book")
    fun deleteBidsFromDatabase(book: String)
    @Query("DELETE FROM crypto_asks WHERE book LIKE :book")
    fun deleteAsksFromDatabase(book: String)
    @Transaction
    fun deleteBidsAndAsksCryptoCoinFromDatabase(book: String){
        deleteBidsFromDatabase(book)
        deleteAsksFromDatabase(book)
    }


    /*@Query("SELECT * FROM crypto_detail WHERE book LIKE :book")
    fun getBidsAndAsksCryptoCoinFromDatabase(book: String): CryptoBidsAsksEntity
    @Insert
    fun insertBidsAndAsksCryptoCoinToDatabase(cryptoBidsCoin: CryptoBidsEntity, cryptoAskCoin: CryptoAsksEntity)
    @Query("DELETE FROM crypto_bids, crypto_asks WHERE book LIKE :book")
    fun deleteBidsAndAsksCryptoCoinFromDatabase(book: String)*/

}