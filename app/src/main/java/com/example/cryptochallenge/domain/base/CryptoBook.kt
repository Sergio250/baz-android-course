package com.example.cryptochallenge.domain.base

data class CryptoBook(
    val asks: List<CryptoAsk> ?= null,
    val bids: List<CryptoBids> ?= null,
    val updated_at: String ?= null,
    val sequence: String ?= null
)

data class CryptoAsk(
    val book: String ?= null,
    val price: String ?= null,
    val amount: String ?= null
)

data class CryptoBids(
    val book: String ?= null,
    val price: String ?= null,
    val amount: String ?= null
)