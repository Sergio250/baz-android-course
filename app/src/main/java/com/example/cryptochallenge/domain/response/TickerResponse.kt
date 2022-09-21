package com.example.cryptochallenge.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("success")
    @Expose
    val success: Boolean? = null,

    @SerializedName("payload")
    @Expose
    val payload: Payload ?= null
){
    data class Payload(
        @SerializedName("book")
        @Expose
        val book: String ?= null,
        @SerializedName("volume")
        @Expose
        val volume: String ?= null,
        @SerializedName("high")
        @Expose
        val high: String ?= null,
        @SerializedName("last")
        @Expose
        val last: String ?= null,
        @SerializedName("low")
        @Expose
        val low: String ?= null,
        @SerializedName("vwap")
        @Expose
        val vwap: String ?= null,
        @SerializedName("ask")
        @Expose
        val ask: String ?= null,
        @SerializedName("bid")
        @Expose
        val bid: String ?= null,
        @SerializedName("created_at")
        @Expose
        val createdAt: String ?= null
    )
}