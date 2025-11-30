package com.example.shopstock.presintation.views.components

import android.util.AttributeSet
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.DropDownList
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.presintation.ItemViewModel
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemContentTextColor
import com.google.android.material.loadingindicator.LoadingIndicator

@Composable
fun ItemScreen(viewModel: ItemViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val items by viewModel.items.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf(DropDownList.DROP_DOWN_LIST.first()) }
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val context = LocalContext.current



    Column(
        modifier = modifier
            .background(Color(0xfff9fafb))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchAndSortCard(
            selectedOption = selectedOption,
            searchText = searchText,
            onSearchTextChange = { newText ->
                searchText = newText
            },
            onSearchSubmit = { searchQuery ->
                viewModel.search(searchQuery)
            },
            onSortSelected = { option ->
                selectedOption = option
                when (option) {
                    DropDownList.ALL -> viewModel.resetSort()
                    DropDownList.QUANTITY_LOW_TO_HIGH -> viewModel.sort(ItemViewModel.SortOrder.ASC)
                    DropDownList.QUANTITY_HIGH_TO_LOW -> viewModel.sort(ItemViewModel.SortOrder.DESC)
                }
            })
        Spacer(modifier = Modifier.height(16.dp))
        when {
            isLoading ->LoadingIndicator()
            error != null -> ErrorScreen(
                error = error.toString()){
                viewModel.retry()
            }
            else -> {
                if (items.isEmpty()) {
                    EmptyScreen()
                } else {
                    ItemsLazyColumn(items=items)
                }
            }
        }
    }
}
@Composable
fun ItemsLazyColumn(items: List<ItemEntity>){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(items) { item ->
            ProductItem(item)
        }
    }
}

@Composable
fun LoadingIndicator(){
    Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth = 8.dp, modifier = Modifier.size(70.dp)
        )
    }
}

@Composable
fun ErrorText(error: String) {
    Text(
        "Something went wrong : $error", style = TextStyle(
            color = ItemContentTextColor, fontFamily = Alexandria, fontSize = ItemTextFontSize
        )
    )
}

@Composable
fun RetryButton(onClick:()->Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            Color.Black.copy(alpha = 0.9f)
        ), onClick = {
           onClick()
        }) {
        Text(
            "Retry", style = TextStyle(
                color = Color.White, fontFamily = Alexandria, fontSize = ItemTextFontSize
            )
        )
    }
}

@Composable
fun ErrorScreen(error: String, onClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        ErrorText(error = error.toString())
        RetryButton{
        onClick()
        }
    }
}