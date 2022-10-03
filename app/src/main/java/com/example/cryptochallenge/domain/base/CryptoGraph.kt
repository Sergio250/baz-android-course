package com.example.cryptochallenge.domain.base

import com.github.mikephil.charting.data.Entry

data class CryptoGraph (
    val book: String ?= null,
    val yValues: ArrayList<Entry> ?= null,
    val xValues: ArrayList<String> ?= null
)