package com.example.mobileshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileshop.ui.MobileShopApp
import com.example.mobileshop.ui.shop.ShopViewModel
import com.example.mobileshop.ui.theme.MobileShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileShopTheme {
                val vm: ShopViewModel = viewModel()
                MobileShopApp(vm)
            }
        }
    }
}
