package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopstock.helpers.DropDownList
import com.example.shopstock.presintation.viewmodel.ItemViewModel

@Composable
fun ItemScreen(viewModel: ItemViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val items by viewModel.items.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf(DropDownList.DROP_DOWN_LIST.first()) }
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

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




