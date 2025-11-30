@file:Suppress("COMPOSE_APPLIER_CALL_MISMATCH")

package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.shopstock.helpers.DropDownList
import com.example.shopstock.helpers.RoundedCorners
import com.example.shopstock.ui.theme.Alexandria

@Composable
fun SortDropdown(
    selectedOption: String, onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val options = DropDownList.DROP_DOWN_LIST

    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(RoundedCorners))
            .fillMaxWidth()
    ) {
        val buttonWidth = maxWidth

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(RoundedCorners),
            colors = ButtonDefaults.buttonColors(Color(0xfff3f3f5)),
            onClick = { expanded = true }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption,
                    style = TextStyle(color = Color.Black, fontFamily = Alexandria)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    Icons.Default.ArrowDropDown, contentDescription = null, tint = Color(0xff717182)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(buttonWidth) // Make dropdown same width as button
                .background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }, text = {
                    Text(
                        text = option,
                        style = TextStyle(color = Color.Black, fontFamily = Alexandria)
                    )
                }, trailingIcon = {
                    if (option == selectedOption) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.Black
                        )
                    }
                })
            }
        }
    }
}
