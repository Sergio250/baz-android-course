@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_detail")
data class CryptoDetailEntity(
    @PrimaryKey
    val book: String,
    val high: String,
    val last: String,
    val low: String,
)
