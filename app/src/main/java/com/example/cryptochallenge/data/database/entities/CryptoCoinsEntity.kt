package com.example.cryptochallenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_coins")
data class CryptoCoinsEntity(
    @PrimaryKey
    val book: String,
    val minAmount: String,
    val maxAmount: String,
    val minPrice: String,
    val maxPrice: String,
    val minValue: String,
    val maxValue: String
)
