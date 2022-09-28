package com.example.cryptochallenge.utils

import com.example.cryptochallenge.data.database.entities.CryptoAsksEntity
import com.example.cryptochallenge.data.database.entities.CryptoBidsEntity
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
        volume = volume,
        high = high,
        last = last,
        low = low,
        vwap = vwap,
        ask = ask,
        bid = bid,
        created_at = createdAt
    )

fun OrderBookResponse.Payload.toDomain() =
    CryptoBook(
        asks = asks?.map { it.toDomain() },
        bids = bids?.map { it.toDomain() },
        updated_at = updated_at,
        sequence = sequence
    )

fun OrderBookResponse.Payload.Asks.toDomain() =
    CryptoAsk(
        book = book,
        price = price,
        amount = amount
    )

fun OrderBookResponse.Payload.Bids.toDomain() =
    CryptoBids(
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
        volume = volume,
        high = high,
        last = last,
        low = low,
        vwap = vwap,
        ask = ask,
        bid = bid,
        created_at = createdAt
    )

fun CryptoBidsEntity.toDomain() =
    CryptoBids(
        book = book,
        price = price,
        amount = amount
    )

fun CryptoAsksEntity.toDomain() =
    CryptoAsk(
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
        volume = volume ?: "",
        high = high ?: "",
        last = last ?: "",
        low = low ?: "",
        vwap = vwap ?: "",
        ask = ask ?: "",
        bid = bid ?: "",
        createdAt = created_at ?: ""
    )

fun CryptoBids.toDatabase() =
    CryptoBidsEntity(
        book = book ?: "",
        price = price ?: "",
        amount = amount ?: ""
    )

fun CryptoAsk.toDatabase() =
    CryptoAsksEntity(
        book = book ?: "",
        price = price ?: "",
        amount = amount ?: ""
    )

