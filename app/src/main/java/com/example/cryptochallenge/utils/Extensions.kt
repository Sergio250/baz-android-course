package com.example.cryptochallenge.utils

import com.example.cryptochallenge.data.database.entities.CryptoBidsAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoCoinsEntity
import com.example.cryptochallenge.data.database.entities.CryptoDetailEntity
import com.example.cryptochallenge.domain.base.*
import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.domain.response.TickerResponse
import java.text.NumberFormat

fun Double.formatAsCurrency(): String = NumberFormat.getCurrencyInstance().format(this)

fun String.format(): String = this.replace("_", " ")

fun String.toCaseLower() : String = this[0] + (this.substring(1, this.length)).lowercase()

fun List<CryptoCoins>.toArrayList() = ArrayList<CryptoCoins>(this)

fun AvailableBooksResponse.Payload.toDomain() =
    CryptoCoins(book = book,
        minAmount = minAmount,
        maxAmount = maxAmount,
        minPrice = minPrice,
        maxPrice = maxPrice,
        minValue = minValue,
        maxValue = maxValue
    )

fun TickerResponse.Payload.toDomain() =
    CryptoDetail(
        book = book,
        high = high,
        last = last,
        low = low
    )

fun TickerResponse.Payload.toDatabase() =
    CryptoDetailEntity(
        book = book ?: "",
        high = high ?: "",
        last = last ?: "",
        low = low ?: ""
    )

fun OrderBookResponse.Payload.toDomain() =
    CryptoData(
        bidsAsks = this.asks?.map { it.toDomain() },
        sequence = this.sequence
    )

fun OrderBookResponse.Payload.BidsOrAsks.toDomain() =
    CryptoBidsAsk(
        book = book,
        price = price,
        amount = amount
    )

fun CryptoCoinsEntity.toDomain() =
    CryptoCoins(book = book,
        minAmount = minAmount,
        maxAmount = maxAmount,
        minPrice = minPrice,
        maxPrice = maxPrice,
        minValue = minValue,
        maxValue = maxValue
    )

fun CryptoDetailEntity.toDomain() =
    CryptoDetail(
        book = book,
        high = high,
        last = last,
        low = low
    )

fun List<CryptoBidsAsk>.toDomain()=
    CryptoData(
        bidsAsks = this
    )

fun CryptoBidsAsksEntity.toDomain() =
    CryptoBidsAsk(
        book = book,
        price = price,
        amount = amount
    )

fun CryptoCoins.toDatabase() =
    CryptoCoinsEntity(
        book = book ?: "",
        minAmount = minAmount ?: "",
        maxAmount = maxAmount ?: "",
        minPrice = minPrice ?: "",
        maxPrice = maxPrice ?: "",
        minValue = minValue ?: "",
        maxValue = maxValue ?: ""
    )

fun CryptoDetail.toDatabase() =
    CryptoDetailEntity(
        book = book ?: "",
        high = high ?: "",
        last = last ?: "",
        low = low ?: ""
    )

fun CryptoBidsAsk.toDatabase() =
    CryptoBidsAsksEntity(
        book = book ?: "",
        price = price ?: "",
        amount = amount ?: ""
    )


