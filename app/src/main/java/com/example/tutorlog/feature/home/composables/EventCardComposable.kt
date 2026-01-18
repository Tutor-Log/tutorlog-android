package com.example.tutorlog.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventCardComposable(
    isRepeat: Boolean,
    time: String,
    meridiem: String,
    title: String,
    subtitle: String,
    description: String,
    onClick: () -> Unit = {}
) {
    val badgeBg = if (isRepeat) ColorBlueBg else Color(0xFF581c87).copy(0.3f)
    val badgeText = if (isRepeat) ColorBlueText else Color(0xFFd8b4fe)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(ColorSurface)
            .border(1.dp, ColorBorder.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Time Column
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = meridiem,
                color = ColorTextSecondary,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        VerticalDivider(modifier = Modifier.height(40.dp), color = Color.Gray)
        Spacer(modifier = Modifier.width(24.dp))


        // Content Column
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    color = badgeBg,
                    shape = RoundedCornerShape(100),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = if (isRepeat) "Weekly" else "Once",
                        color = badgeText,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                text = subtitle,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp)
            )

            if (description.isNotEmpty()) {
                Text(
                    text = description,
                    color = ColorTextSecondary,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF111827)
@Composable
private fun PreviewEventCard() {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Preview 1: Private Class
        EventCardComposable(
            time = "09:00",
            meridiem = "AM",
            title = "Jane Doe",
            subtitle = "Violin • Grade 3",
            description = "Reviewing Vivaldi Concerto in A Minor.",
            isRepeat = false
        )

        // Preview 2: Group Class
        EventCardComposable(
            time = "10:00",
            meridiem = "AM",
            title = "Advanced Quartet",
            subtitle = "Ensemble Rehearsal",
            description = "Preparing for Winter Recital.",
            isRepeat = true
        )

    }
}