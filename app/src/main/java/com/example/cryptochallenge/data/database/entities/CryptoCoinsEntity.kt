package com.example.cryptochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_coins")
data class CryptoCoinsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "minimum_amount") val minAmount: String,
    @ColumnInfo(name = "maximum_amount") val maxAmount: String,
    @ColumnInfo(name = "minimum_price") val minPrice: String,
    @ColumnInfo(name = "maximum_price") val maxPrice: String,
    @ColumnInfo(name = "minimum_value") val minValue: String,
    @ColumnInfo(name = "maximum_value") val maxValue: String
)
