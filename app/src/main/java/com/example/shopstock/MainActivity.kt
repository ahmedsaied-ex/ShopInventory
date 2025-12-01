package com.example.shopstock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopstock.presintation.views.components.ItemScreen
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.MainBackground
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
                    topBar = { AppBar() },
                    modifier = Modifier
                        .background(MainBackground)
                        .fillMaxSize()
                ) { innerPadding ->
                    ItemScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Preview
@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainBackground)
            .windowInsetsPadding(WindowInsets.statusBars),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(Modifier.width(5.dp))
        Text("Inventory App", style = TextStyle(color = Color.Black, fontFamily = Alexandria, fontSize = 15.sp))


    }
}