package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopstock.helpers.BannerImageSize
import com.example.shopstock.helpers.CardElevation
import com.example.shopstock.helpers.RoundedCorners

@Composable
fun SearchAndSortCard(
    selectedOption: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: (String) -> Unit,  // New parameter for search submit
    onSortSelected: (String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White, contentColor = Color.Black
        ),
        shape = RoundedCornerShape(RoundedCorners),

        elevation = CardDefaults.cardElevation(CardElevation),
        modifier = Modifier.fillMaxWidth().padding(vertical = CardElevation)
    ) {
        Column(modifier = Modifier.padding(BannerImageSize)) {
            // Search bar
            SearchTextField(
                searchedText = searchText,
                onChanged = onSearchTextChange,
                onSearchSubmit = onSearchSubmit  // Pass submit handler
            )
            Spacer(modifier = Modifier.height(BannerImageSize))
            // Dropdown for sort options
            SortDropdown(
                selectedOption = selectedOption, onOptionSelected = onSortSelected
            )
        }
    }
}