package com.example.tutorlog.feature.students.add_student

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorlog.design.LocalColors

@Composable
fun AddPupilScreen(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = LocalColors.BackgroundDefaultDark
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = value,
            onValueChange = {
                onValueChange.invoke(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .padding(horizontal = 24.dp),
            colors = TextFieldDefaults.colors(
                cursorColor = LocalColors.Black,
                focusedTextColor = LocalColors.Black,
                unfocusedTextColor = LocalColors.Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedLabelColor = LocalColors.Black,
                unfocusedLabelColor = LocalColors.Black,
                errorLabelColor = LocalColors.Red300,
                disabledTextColor = LocalColors.Black,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = LocalColors.LightGreen,
                unfocusedContainerColor = LocalColors.LightGreen,
            ),
            label = {
                Text(
                    text = "Name",
                    color = LocalColors.Gray500
                )
            }
        )
    }
}

@Preview
@Composable
private fun PreviewAddPupilScreen() {
    AddPupilScreen(
        value = "samarth",
        onValueChange = {}
    )
}