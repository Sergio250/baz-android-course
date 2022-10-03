package com.example.cryptochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_bids_asks")
data class CryptoBidsAsksEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Int = 0,
    @ColumnInfo val book: String,
    @ColumnInfo val price: String,
    @ColumnInfo val amount: String
)
