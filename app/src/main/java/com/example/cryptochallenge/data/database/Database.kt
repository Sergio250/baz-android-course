package com.example.cryptochallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptochallenge.data.database.dao.CryptoDaoInterface
import com.example.cryptochallenge.data.database.entities.CryptoAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoBidsEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity

@Database(entities = [CryptoCoinsEntity::class, CryptoDetailEntity::class, CryptoBidsEntity::class, CryptoAsksEntity::class], version = 2)
abstract class Database: RoomDatabase() {
    abstract fun getCryptoDao(): CryptoDaoInterface
}