package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.shopstock.R
import com.example.shopstock.helpers.BorderThickness
import com.example.shopstock.helpers.IconSize
import com.example.shopstock.helpers.RoundedCorners
import com.example.shopstock.helpers.SmallHorizontalPadding
import com.example.shopstock.helpers.SmallVerticalPadding
import com.example.shopstock.helpers.StockNumberFont
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.StableBackground
import com.example.shopstock.ui.theme.StableBorder
import com.example.shopstock.ui.theme.StableTextColor
import com.example.shopstock.ui.theme.WarningBackground
import com.example.shopstock.ui.theme.WarningBorder
import com.example.shopstock.ui.theme.WarningTextColor


@Composable
@Preview(showBackground = false)
fun Warning() {
    Box(
        modifier = Modifier, contentAlignment = Alignment.Center

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = BorderThickness,
                    color = WarningBorder,
                    shape = RoundedCornerShape(RoundedCorners)  // Same shape as background
                )
                .background(
                    color = WarningBackground,
                    shape = RoundedCornerShape(RoundedCorners) // Rounded corners
                )
                .padding(horizontal = SmallHorizontalPadding, SmallVerticalPadding),
        ) {
            Text(
                text = "20/60 (37%) ", style = TextStyle(
                    fontSize = StockNumberFont,
                    fontFamily = Alexandria,
                    color = WarningTextColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Image(
                painter = painterResource(R.drawable.ic_warning),
                contentDescription = "",
                modifier = Modifier.size(IconSize)
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun Stable() {
    Box(
        modifier = Modifier, contentAlignment = Alignment.Center

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = BorderThickness,
                    color = StableBorder,
                    shape = RoundedCornerShape(RoundedCorners)  // Same shape as background
                )
                .background(
                    color = StableBackground,
                    shape = RoundedCornerShape(RoundedCorners) // Rounded corners
                )
                .padding(horizontal = SmallHorizontalPadding, SmallVerticalPadding),
        ) {
            Text(
                text = "20/60 (37%) ", style = TextStyle(
                    fontSize = StockNumberFont,
                    fontFamily = Alexandria,
                    color = StableTextColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Image(
                painter = painterResource(R.drawable.ic_agreed),
                contentDescription = "",
                modifier = Modifier.size(IconSize)
            )
        }
    }
}