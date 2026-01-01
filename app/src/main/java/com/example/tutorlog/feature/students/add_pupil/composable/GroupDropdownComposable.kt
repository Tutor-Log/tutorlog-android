package com.example.tutorlog.feature.students.add_pupil.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tutorlog.design.LocalColors


@Composable
fun GroupDropdownComposable(
    selectedGroup: String,
    onGroupSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("None (Individual Lesson)", "Advanced Violins", "Beginner's Quartet", "Intermediate Ensemble")

    Box(modifier = Modifier.fillMaxWidth()) {
        // We reuse the custom text field but make it read-only and clickable
        CustomTextFieldComposable(
            value = selectedGroup.ifEmpty { "" },
            onValueChange = {},
            label = "Assign to Group",
            placeholder = "None (Individual Lesson)",
            icon = Icons.Default.Home,
            readOnly = true,
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(LocalColors.Gray800)
                .border(1.dp, LocalColors.Gray700, RoundedCornerShape(4.dp))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, color = Color.White) },
                    onClick = {
                        onGroupSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}