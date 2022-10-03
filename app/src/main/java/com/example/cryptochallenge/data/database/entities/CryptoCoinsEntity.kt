package com.example.cryptochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_coins")
data class CryptoCoinsEntity (
    @PrimaryKey
    @ColumnInfo val book: String,
    @ColumnInfo val minAmount: String,
    @ColumnInfo val maxAmount: String,
    @ColumnInfo val minPrice: String,
    @ColumnInfo val maxPrice: String,
    @ColumnInfo val minValue: String,
    @ColumnInfo val maxValue: String
)
