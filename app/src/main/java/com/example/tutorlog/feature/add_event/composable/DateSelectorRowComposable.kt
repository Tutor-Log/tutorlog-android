package com.example.tutorlog.feature.add_event.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlog.R
import com.example.tutorlog.design.LocalColors

@Composable
fun DateSelectorRowComposable(
    label: String,
    onClick: () -> Unit,
    date: String,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = LocalColors.Gray400,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LocalColors.Gray800, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = LocalColors.Gray700,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 16.dp, horizontal = 12.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = {
                        onClick.invoke()
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "null",
                tint = LocalColors.Gray400,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (date.isNotEmpty()) {
                Text(
                    text = date,
                    color = LocalColors.Gray400,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            } else {
                Text(
                    text = "select date...",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}