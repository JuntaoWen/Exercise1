package com.example.mobileshop.ui.shop

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.mobileshop.data.CartItem
import com.example.mobileshop.data.SampleCatalog
import com.example.mobileshop.data.Shoe
import com.example.mobileshop.data.ShoeColor

/**
 * Holds the hardcoded catalog and shopping bag. Total price is derived from cart lines.
 */
class ShopViewModel : ViewModel() {

    /** Immutable snapshot of products — replace or extend [SampleCatalog] as needed. */
    val catalog: List<Shoe> = SampleCatalog.shoes

    private val _cartItems: SnapshotStateList<CartItem> = mutableStateListOf()
    val cartItems: SnapshotStateList<CartItem> get() = _cartItems

    val cartSubtotal: Double
        get() = _cartItems.sumOf { it.lineTotal }

    val cartItemCount: Int
        get() = _cartItems.sumOf { it.quantity }

    fun addToBag(shoe: Shoe, color: ShoeColor, size: Int, quantity: Int = 1) {
        require(quantity > 0)
        val variantKey = "${shoe.id}|${color.id}|$size"
        val idx = _cartItems.indexOfFirst { it.variantKey == variantKey }
        if (idx >= 0) {
            val existing = _cartItems[idx]
            _cartItems[idx] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            _cartItems.add(
                CartItem(
                    shoe = shoe,
                    selectedColor = color,
                    selectedSize = size,
                    quantity = quantity,
                ),
            )
        }
    }

    /** Quick add from the grid using defaults (first color, first size). */
    fun quickAddFromFeed(shoe: Shoe, quantity: Int = 1) {
        val color = shoe.colors.firstOrNull() ?: return
        val size = shoe.sizes.minOrNull() ?: return
        addToBag(shoe, color, size, quantity)
    }

    fun updateQuantity(item: CartItem, newQuantity: Int) {
        val idx = _cartItems.indexOfFirst { it.variantKey == item.variantKey }
        if (idx < 0) return
        if (newQuantity <= 0) {
            _cartItems.removeAt(idx)
        } else {
            _cartItems[idx] = item.copy(quantity = newQuantity)
        }
    }

    fun removeFromBag(item: CartItem) {
        _cartItems.removeAll { it.variantKey == item.variantKey }
    }

    fun clearBag() {
        _cartItems.clear()
    }
}
