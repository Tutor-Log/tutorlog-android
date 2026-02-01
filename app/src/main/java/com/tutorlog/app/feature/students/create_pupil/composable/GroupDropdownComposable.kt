package com.tutorlog.app.feature.students.create_pupil.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.design.LocalColors


@Composable
fun GroupDropdownComposable(
    selectedGroup: String,
    onGroupSelected: (String) -> Unit,
    optionList: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    Column(

    ) {
        Text(
            text = "Assign to Group",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = {
                        expanded = !expanded
                    }
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(color = LocalColors.Gray800, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    text = selectedGroup,
                    color = if (selectedGroup == "None (Individual Lesson)") Color.Gray else Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(LocalColors.Gray800)
                    .border(1.dp, LocalColors.Gray700, RoundedCornerShape(4.dp))
            ) {
                optionList.forEach { option ->
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

}

@Preview
@Composable
private fun GroupPreview() {
    GroupDropdownComposable(
        selectedGroup = "Advanced Violins",
        onGroupSelected = {},
        optionList = listOf(
            "None (Individual Lesson)",
            "Advanced Violins",
            "Beginner's Quartet",
            "Intermediate Ensemble"
        )
    )
}