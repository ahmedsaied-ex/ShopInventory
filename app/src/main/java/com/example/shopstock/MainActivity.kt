package com.example.shopstock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.shopstock.presintation.views.components.ItemScreen
import com.example.shopstock.ui.theme.ShopStockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopStockTheme {
                Scaffold(
                    modifier = Modifier
                        .background(Color(0xfff9fafb))
                        .fillMaxSize()
                ) { innerPadding ->
                    ItemScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

}


