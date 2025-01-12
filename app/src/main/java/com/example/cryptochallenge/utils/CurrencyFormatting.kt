package com.example.cryptochallenge.utils

import java.text.NumberFormat

fun Double.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}