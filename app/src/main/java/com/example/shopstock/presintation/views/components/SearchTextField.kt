package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopstock.helpers.RoundedCorners
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.shopstock.R
import com.example.shopstock.helpers.BorderThickness
import com.example.shopstock.helpers.Zero
import com.example.shopstock.ui.theme.HandelColor
import com.example.shopstock.ui.theme.SelectionTextColor
import com.example.shopstock.ui.theme.TextFieldBackground
import com.example.shopstock.ui.theme.TextFieldBorderColor

@Composable
fun SearchTextField(
    searchedText: String,
    onChanged: (String) -> Unit,
    onSearchSubmit: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isFocused)BorderThickness else Zero,
                color = if (isFocused)   TextFieldBorderColor else Color.Transparent,
                shape = RoundedCornerShape(RoundedCorners)
            )
    ) {
        TextField(
            value = searchedText,
            onValueChange = { newText ->
                onChanged(newText)
                // If text becomes empty, trigger search to show all items
                if (newText.isBlank()) {
                    onSearchSubmit("")
                }
            },
            placeholder = { Text(stringResource(R.string.search)) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search, contentDescription = "Search", tint = Color(0xff99a1af)
                )
            },
            trailingIcon = {
                if (searchedText.isNotEmpty()) {
                    IconButton(onClick = {
                        onChanged("")
                        onSearchSubmit("")  // Show all items
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = stringResource(R.string.clear_icon),
                            tint = Color(0xff99a1af)
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldBackground,
                unfocusedContainerColor = TextFieldBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color.Black,
                selectionColors = TextSelectionColors(
                    handleColor = HandelColor, backgroundColor = SelectionTextColor
                )
            ),
            singleLine = true,
            shape = RoundedCornerShape(RoundedCorners),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchSubmit(searchedText)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
        )
    }
}