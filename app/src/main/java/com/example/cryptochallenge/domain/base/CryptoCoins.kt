package com.example.cryptochallenge.domain.base

data class CryptoCoins(
    val book: String ? = null,
    val minAmount: String ? = null,
    val maxAmount: String ? = null,
    val minPrice: String ? = null,
    val maxPrice: String ? = null,
    val minValue: String ? = null,
    val maxValue: String ? = null
)
