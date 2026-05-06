package com.example.mobileshop.ui.util

import java.text.NumberFormat
import java.util.Locale

fun Double.formatUsd(): String =
    NumberFormat.getCurrencyInstance(Locale.CANADA).format(this)
