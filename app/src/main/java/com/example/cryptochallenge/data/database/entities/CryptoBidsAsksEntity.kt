package com.example.cryptochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_bids")
data class CryptoBidsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "amount") val amount: String
)

@Entity(tableName = "crypto_asks")
data class CryptoAsksEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "amount") val amount: String
)

