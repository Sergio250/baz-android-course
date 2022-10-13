package com.example.cryptochallenge.network.models

data class TickerResponse(
    val success: Boolean? = null,
    val payload: Payload? = null
) {
    data class Payload(
        val book: String ? = null,
        val high: String ? = null,
        val last: String ? = null,
        val low: String ? = null,
    )
}
