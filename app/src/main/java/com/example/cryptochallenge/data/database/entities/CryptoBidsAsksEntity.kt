package com.example.cryptochallenge.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_bids_asks")
data class CryptoBidsAsksEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val book: String,
    val price: String,
    val amount: String
)
