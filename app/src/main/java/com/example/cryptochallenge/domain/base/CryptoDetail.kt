@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.domain.base

data class CryptoDetail(
    val book: String ?= null,
    val high: String ?= null,
    val last: String ?= null,
    val low: String ?= null,
)

