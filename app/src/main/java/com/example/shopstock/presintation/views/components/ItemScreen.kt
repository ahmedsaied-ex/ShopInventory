package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopstock.helpers.DropDownList
import com.example.shopstock.helpers.MediumSpacerHeight
import com.example.shopstock.presintation.viewmodel.ItemViewModel
import com.example.shopstock.ui.theme.MainBackground

@Composable
fun ItemScreen(viewModel: ItemViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val items by viewModel.items.collectAsState()
    var searchText by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf(DropDownList.DROP_DOWN_LIST.first()) }
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LazyColumn(
        modifier = modifier
            .background(MainBackground)
            .fillMaxSize()
            .padding(horizontal = MediumSpacerHeight)
    ) {
        item{
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
        }
        when {
            isLoading -> item{
                LoadingIndicator()
            }
            error != null -> item{
                ErrorScreen(
                    error = error.toString()
                ) {
                    viewModel.retry()
                }
            }
            else -> {
                if (items.isEmpty()) {
                    item{ EmptyScreen() }
                } else {
                    items(items) { item ->
                        ProductItem(item)
                    }
                }
            }
        }
    }
}




