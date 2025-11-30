package com.example.shopstock.helpers

import androidx.compose.ui.graphics.Color
import com.example.shopstock.R
import com.example.shopstock.ui.theme.BackgroundCritical
import com.example.shopstock.ui.theme.BorderCritical
import com.example.shopstock.ui.theme.CriticalTextColor
import com.example.shopstock.ui.theme.StableBackground
import com.example.shopstock.ui.theme.StableBorder
import com.example.shopstock.ui.theme.StableTextColor
import com.example.shopstock.ui.theme.WarningBackground
import com.example.shopstock.ui.theme.WarningBorder
import com.example.shopstock.ui.theme.WarningTextColor


enum class State{
    STABLE,
    CRITICAL,
    WARNING
}
data class StatusRaw(
    val backgroundColor: Color,
    val borderColor: Color,
    val textColor: Color,
    val icon: Int
)

// Map State to StatusRaw
fun State.toStatusRaw(): StatusRaw = when (this) {
    State.STABLE -> StatusRaw(
        backgroundColor = StableBackground,
        borderColor = StableBorder,
        textColor = StableTextColor,
        icon = R.drawable.ic_agreed
    )
    State.CRITICAL -> StatusRaw(
        backgroundColor = BackgroundCritical,
        borderColor = BorderCritical,
        textColor = CriticalTextColor,
        icon = R.drawable.ic_alert
    )
    State.WARNING -> StatusRaw(
        backgroundColor = WarningBackground,
        borderColor = WarningBorder,
        textColor = WarningTextColor,
        icon = R.drawable.ic_warning
    )
}
