package com.example.mobileshop.data

/**
 * A selectable color variant for a shoe.
 * [argb] is a full ARGB value (e.g. `0xFFFF5722`); use `androidx.compose.ui.graphics.Color(argb)` in Compose.
 */
data class ShoeColor(
    val id: String,
    val name: String,
    val argb: Long,
)
