package com.example.cryptochallenge.domain.base

data class CryptoDetail(
    val book: String ?= null,
    val volume: String ?= null,
    val high: String ?= null,
    val last: String ?= null,
    val low: String ?= null,
    val vwap: String ?= null,
    val ask: String ?= null,
    val bid: String ?= null,
    val created_at: String ?= null
)

