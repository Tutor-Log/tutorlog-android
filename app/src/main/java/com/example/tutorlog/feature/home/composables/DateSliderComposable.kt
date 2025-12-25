package com.example.tutorlog.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.LocalTypography
import com.example.tutorlog.domain.local.UIDateInfo

@Composable
fun DateSliderComposable(
    dateInfoList: List<UIDateInfo>,
    selectedInt: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        LazyRow(modifier = Modifier) {
            dateInfoList.forEachIndexed { index, item ->
                item {
                    if (index == 0) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Column(
                        modifier = Modifier
                            .clickable {
                                onClick.invoke(index)
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.day.firstOrNull().toString(),
                            style = LocalTypography.headingSmall14,
                            color = LocalColors.Neutral300
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(40.dp)
                                .background(
                                    color = if (selectedInt == index) LocalColors.LightGreen else Color.Transparent,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.date,
                                style = LocalTypography.headingSmall14,
                                color = if (selectedInt == index) LocalColors.Black else LocalColors.White
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .width(46.dp)
                    .height(1.dp)
                    .background(LocalColors.Neutral300, shape = RoundedCornerShape(16.dp))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}


@Preview
@Composable
private fun PreviewDateSliderComposable() {
    DateSliderComposable(
        modifier = Modifier.background(LocalColors.BackgroundDefaultDark).padding(vertical = 16.dp),
        dateInfoList = listOf(
            UIDateInfo(date = "8", day = "Sun"),
            UIDateInfo(date = "9", day = "Mon"),
            UIDateInfo(date = "10", day = "Tue"),
            UIDateInfo(date = "11", day = "Wed"),
            UIDateInfo(date = "12", day = "Thu"),
            UIDateInfo(date = "13", day = "Fri"),
            UIDateInfo(date = "14", day = "Sat"),
            UIDateInfo(date = "15", day = "Sun"),
            UIDateInfo(date = "16", day = "Mon"),
        ),
        selectedInt = 3,
        onClick = {

        }
    )
}