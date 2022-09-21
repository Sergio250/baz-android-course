package com.example.cryptochallenge.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean? = null,

    @SerializedName("payload")
    @Expose
    val payload: List<Payload> ?= null
) {
    data class Payload(
        @SerializedName("book")
        @Expose
        val book: String ?= null,
        @SerializedName("minimum_amount")
        @Expose
        val minAmount: String ?= null,
        @SerializedName("maximum_amount")
        @Expose
        val maxAmount: String ?= null,
        @SerializedName("minimum_price")
        @Expose
        val minPrice: String ?= null,
        @SerializedName("maximum_price")
        @Expose
        val maxPrice: String ?= null,
        @SerializedName("minimum_value")
        @Expose
        val minValue: String ?= null,
        @SerializedName("maximum_value")
        @Expose
        val maxValue: String ?= null
    )
}