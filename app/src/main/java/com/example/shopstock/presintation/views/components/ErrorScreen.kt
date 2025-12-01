package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ErrorScreen(error: String, onClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        ErrorText(error = error)
        RetryButton{
            onClick()
        }
    }
}