package com.example.mobileshop.data

/**
 * One line in the shopping bag for a specific shoe, color, and size.
 * Quantity lets the same variant be merged when adding repeatedly.
 */
data class CartItem(
    val shoe: Shoe,
    val selectedColor: ShoeColor,
    val selectedSize: Int,
    val quantity: Int = 1,
) {
    init {
        require(quantity > 0) { "quantity must be positive" }
        require(shoe.sizes.contains(selectedSize)) { "size must be one of the shoe's sizes" }
        require(shoe.colors.any { it.id == selectedColor.id }) { "color must belong to the shoe" }
    }

    val lineTotal: Double
        get() = shoe.price * quantity

    /** Stable key for Lazy lists / deduping identical variants. */
    val variantKey: String
        get() = "${shoe.id}|${selectedColor.id}|$selectedSize"
}
