package com.example.cryptochallenge.domain.response

import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    val success: Boolean? = null,
    val payload: List<Payload> ? = null
) {
    data class Payload(
        val book: String ? = null,
        val minAmount: String ? = null,
        val maxAmount: String ? = null,
        @SerializedName("minimum_price")
        val minPrice: String ? = null,
        @SerializedName("maximum_price")
        val maxPrice: String ? = null,
        val minValue: String ? = null,
        val maxValue: String ? = null
    )
}
