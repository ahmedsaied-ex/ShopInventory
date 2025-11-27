package com.example.shopstock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopstock.helpers.CardIconSize
import com.example.shopstock.helpers.SmallHorizontalPadding
import com.example.shopstock.helpers.SmallVerticalPadding
import com.example.shopstock.presintation.ItemViewModel
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.BackgroundCritical
import com.example.shopstock.ui.theme.BorderCritical
import com.example.shopstock.ui.theme.ShopStockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopStockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ItemScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun ItemScreen(viewModel: ItemViewModel = hiltViewModel(),modifier: Modifier) {
    val items by viewModel.items.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { viewModel.sortAsc() }) {
                Text("Sort ASC")
            }
            Button(onClick = { viewModel.sortDesc() }) {
                Text("Sort DESC")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = searchText, onValueChange = {
                    searchText = it
                },
                label = { Text("Search by name") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.searchByName(searchText) }) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // عرض نتيجة البحث لو موجودة
        searchResult?.let { item ->
            item.forEach {
                Text("${it?.name}: ${it?.itemLeftInInventory}")
                Divider()
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Text("${item.name}: ${item.itemLeftInInventory}")
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, color = Color(0xffe8e8e8), shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = "Name", style = TextStyle(
                color = Color(0xff2e2e2e),
                fontFamily = Alexandria,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.dollar_sign),
                    contentDescription = "dollar sign",
                    modifier = Modifier.size(CardIconSize)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Price", style = TextStyle(
                        color = Color(0xff666666),
                        fontFamily = Alexandria,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "$199.5", style = TextStyle(
                        color = Color(0xff9e9e9e),
                        fontFamily = Alexandria,
                        fontSize = 12.sp,
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.archive),
                    contentDescription = "dollar sign",
                    modifier = Modifier.size(CardIconSize)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "In Stock ", style = TextStyle(
                        color = Color(0xff666666),
                        fontFamily = Alexandria,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                    )
                )
                InStock()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.predict_selling),
                    contentDescription = "Predict Chart",
                    modifier = Modifier.size(CardIconSize)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Predict Sales (Tomorrow) ", style = TextStyle(
                        color = Color(0xff666666),
                        fontFamily = Alexandria,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                    )
                )
            }


        }


    }
}


@Composable
@Preview(showBackground = false)
fun InStock() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = BorderCritical,
                    shape = RoundedCornerShape(30.dp)  // Same shape as background
                )
                .background(
                    color = BackgroundCritical, shape = RoundedCornerShape(30.dp) // Rounded corners
                )
                .padding(horizontal = SmallHorizontalPadding, SmallVerticalPadding),
        ) {
            Text(
                text = "20/60 (37%) ", style = TextStyle(
                    fontSize = 8.sp,
                    fontFamily = Alexandria,
                    color = Color(0xff871515),
                    fontWeight = FontWeight.SemiBold
                )
            )
            Image(
                painter = painterResource(R.drawable.cancelled_session_ic),
                contentDescription = "",
                modifier = Modifier.size(11.dp)
            )
        }


    }

}



