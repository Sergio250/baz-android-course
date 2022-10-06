package com.example.cryptochallenge.domain.base

data class CryptoData(
    val bidsAsks: List<CryptoBidsAsk> ? = null,
    val updatedAt: String ? = null,
    val sequence: String ? = null
)

data class CryptoBidsAsk(
    val book: String ? = null,
    val price: String ? = null,
    val amount: String ? = null
)
