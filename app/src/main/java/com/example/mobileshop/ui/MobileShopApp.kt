package com.example.mobileshop.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.mobileshop.ui.screens.BagScreen
import com.example.mobileshop.ui.screens.HomeScreen
import com.example.mobileshop.ui.screens.ProductDetailScreen
import com.example.mobileshop.ui.screens.SplashScreen
import com.example.mobileshop.ui.shop.ShopViewModel

/**
 * Simple navigation without Navigation-Compose: splash → home ↔ detail using saveable strings.
 */
@Composable
fun MobileShopApp(vm: ShopViewModel) {
    var showSplash by rememberSaveable { mutableStateOf(true) }
    var detailShoeId by rememberSaveable { mutableStateOf<String?>(null) }
    var bagOpen by rememberSaveable { mutableStateOf(false) }
    var bagReturnDetailId by rememberSaveable { mutableStateOf<String?>(null) }

    val cartTotal = vm.cartSubtotal

    when {
        showSplash -> SplashScreen(onGetStarted = { showSplash = false })
        else -> {
            if (bagOpen) {
                BagScreen(
                    cartItems = vm.cartItems,
                    subtotal = cartTotal,
                    onBack = {
                        bagOpen = false
                        detailShoeId = bagReturnDetailId
                        bagReturnDetailId = null
                    },
                    onDeleteItem = { item -> vm.removeFromBag(item) },
                )
                return
            }

            val openId = detailShoeId
            val shoe = openId?.let { id -> vm.catalog.find { it.id == id } }

            LaunchedEffect(openId, shoe) {
                if (openId != null && shoe == null) detailShoeId = null
            }

            if (shoe != null) {
                ProductDetailScreen(
                    shoe = shoe,
                    cartTotal = cartTotal,
                    onBack = { detailShoeId = null },
                    onBagClick = {
                        bagOpen = true
                        bagReturnDetailId = shoe.id
                    },
                    onAddToBag = { color, size -> vm.addToBag(shoe, color, size) },
                )
            } else {
                HomeScreen(
                    catalog = vm.catalog,
                    onProductClick = { detailShoeId = it.id },
                    onQuickAdd = { vm.quickAddFromFeed(it) },
                    onBagClick = {
                        bagOpen = true
                        bagReturnDetailId = null
                    },
                )
            }
        }
    }
}
