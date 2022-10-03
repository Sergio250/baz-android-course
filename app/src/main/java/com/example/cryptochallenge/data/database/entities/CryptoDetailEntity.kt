@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_detail")
data class CryptoDetailEntity(
    @PrimaryKey
    @ColumnInfo val book: String,
    @ColumnInfo val high: String,
    @ColumnInfo val last: String,
    @ColumnInfo val low: String,
)