package com.example.cryptochallenge.domain.base

import com.github.mikephil.charting.data.Entry

data class CryptoGraph (
    val book: String ?= null,
    val asksYValues: ArrayList<Entry> ?= null,
    val bidsYValues: ArrayList<Entry> ?= null,
    val xValues: ArrayList<String> ?= null,
    val labels: ArrayList<String> ?= null,
    val colors: ArrayList<Int> ?= null
)