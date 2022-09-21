package com.example.cryptochallenge.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean? = null,
    @SerializedName("payload")
    @Expose
    val payload: Payload?= null
){
    data class Payload(
        @SerializedName("asks")
        @Expose
        val asks: List<Asks> ?= null,
        @SerializedName("bids")
        @Expose
        val bids: List<Bids> ?= null,
        @SerializedName("updated_at")
        @Expose
        val updated_at: String ?= null,
        @SerializedName("sequence")
        @Expose
        val sequence: String ?= null,
    ) {
        data class Asks(
            @SerializedName("book")
            @Expose
            val book: String ?= null,
            @SerializedName("price")
            @Expose
            val price: String ?= null,
            @SerializedName("amount")
            @Expose
            val amount: String ?= null
        )

        data class Bids(
            @SerializedName("book")
            @Expose
            val book: String ?= null,
            @SerializedName("price")
            @Expose
            val price: String ?= null,
            @SerializedName("amount")
            @Expose
            val amount: String ?= null
        )
    }
}