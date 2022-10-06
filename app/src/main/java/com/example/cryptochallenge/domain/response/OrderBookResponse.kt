package com.example.cryptochallenge.domain.response

data class OrderBookResponse(
    val success: Boolean? = null,
    val payload: Payload? = null
) {
    data class Payload(
        val asks: List<BidsOrAsks> ? = null,
        val bids: List<BidsOrAsks> ? = null,
        val sequence: String ? = null,
    ) {
        data class BidsOrAsks(
            val book: String ? = null,
            val price: String ? = null,
            val amount: String ? = null
        )
    }
}
