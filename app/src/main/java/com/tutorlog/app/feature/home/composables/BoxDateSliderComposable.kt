package com.tutorlog.app.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.domain.model.local.UIDateInfo

@Composable
fun DateSliderComposable(
    dayList: List<UIDateInfo>,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        items(dayList) { day ->
            DateSliderItemComposable(
                day = day.day,
                date = day.date,
                isSelected = day.isSelected,
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = {
                        onClick.invoke(day.dateInMillis)
                    }
                )
            )
        }
    }
}

@Composable
fun DateSliderItemComposable(
    day: String,
    date: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) ColorPrimary else ColorSurface
    val textColorDay = if (isSelected) Color.Black else ColorTextSecondary
    val textColorNum = if (isSelected) Color.Black else ColorTextPrimary
    val border =
        if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, ColorBorder)

    Column(
        modifier = modifier
            .width(48.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .then(
                if (border != null) Modifier.border(
                    border,
                    RoundedCornerShape(16.dp)
                ) else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = day,
            fontSize = 12.sp,
            color = textColorDay,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
        Text(
            text = date,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColorNum
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateSlider() {
    DateSliderComposable(
        dayList = listOf(
            UIDateInfo(date = "8", day = "Sun", isSelected = true, dateInMillis = 0L),
            UIDateInfo(date = "9", day = "Mon", isSelected = false, dateInMillis = 0L),
        ),
        onClick = {
            
        }
    )
}