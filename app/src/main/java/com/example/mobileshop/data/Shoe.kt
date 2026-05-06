package com.example.mobileshop.data

import androidx.annotation.DrawableRes

data class Shoe(
    val id: String,
    val name: String,
    val brandLine: String,
    val description: String,
    val price: Double,
    val rating: Float,
    val category: String,
    val colors: List<ShoeColor>,
    val sizes: List<Int>,
    @DrawableRes val imageRes: Int,
)
