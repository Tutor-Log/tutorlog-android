package com.example.tutorlog.feature.add_event.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.utils.shake

@Composable
fun DayCircle(
    selectedList: List<Int>,
    onClick: (Int) -> Unit,
    isError: Boolean = false,
    shakeTrigger: Int = 0
) {
    val dayList = listOf("S", "M", "T", "W", "TH", "F", "S")
    Column(
        modifier = Modifier
            .shake(trigger = shakeTrigger, enabled = isError)
            .border(
                width = if (isError) 1.dp else 0.dp,
                color = if (isError) LocalColors.Red500 else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(if (isError) 8.dp else 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dayList.forEachIndexed { index, day ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (index in selectedList) LocalColors.PrimaryGreen else LocalColors.Gray800)
                        .border(
                            1.dp,
                            if (index in selectedList) LocalColors.PrimaryGreen else LocalColors.Gray700,
                            CircleShape
                        )
                        .clickable(onClick = { onClick.invoke(index) }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day,
                        color = if (index in selectedList) Color.Black else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDayList() {
    DayCircle(
        selectedList = listOf(1,4,5),
        onClick = {}
    )
}