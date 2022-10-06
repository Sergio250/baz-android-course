package com.example.cryptochallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptochallenge.data.database.dao.CryptoDaoInterface
import com.example.cryptochallenge.data.database.entities.CryptoBidsAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity

@Database(entities = [CryptoCoinsEntity::class, CryptoDetailEntity::class, CryptoBidsAsksEntity::class], version = 6)
abstract class Database : RoomDatabase() {
    abstract fun getCryptoDao(): CryptoDaoInterface
}
