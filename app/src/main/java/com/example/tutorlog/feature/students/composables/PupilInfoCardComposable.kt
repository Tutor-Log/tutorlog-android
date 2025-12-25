package com.example.tutorlog.feature.students.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.LocalTypography

@Composable
fun PupilInfoCardComposable(
    name: String,
    phoneNumber: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xff1f2937),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = LocalColors.White,
                        shape = CircleShape
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.first().toString(),
                    color = LocalColors.Red300,
                    style = LocalTypography.headingMedium16
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    color = LocalColors.White,
                    style = LocalTypography.bodyMedium14
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = phoneNumber,
                    color = LocalColors.Neutral300,
                    style = LocalTypography.bodySmall12
                )
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = LocalColors.White
        )
    }

}

@Composable
fun GroupInfoCardComposable(
    name: String,
    memberCount: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(
                    color = LocalColors.Red300, shape = RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff1f2937),
                    shape = RoundedCornerShape(
                        bottomEnd = 4.dp,
                        topEnd = 4.dp
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(
                            color = LocalColors.White,
                            shape = CircleShape
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.first().toString(),
                        color = LocalColors.Red300,
                        style = LocalTypography.headingMedium16
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = name,
                        color = LocalColors.White,
                        style = LocalTypography.bodyMedium14
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = memberCount,
                        color = LocalColors.Neutral300,
                        style = LocalTypography.bodySmall12
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = LocalColors.White
            )
        }
    }

}

@Preview
@Composable
private fun PreviewPupilInfoComposable() {
    Column {
        PupilInfoCardComposable(
            name = "John Doe",
            phoneNumber = "+1234567890"
        )
        Spacer(modifier = Modifier.height(16.dp))
        PupilInfoCardComposable(
            name = "John Doe",
            phoneNumber = "+1234567890"
        )
        Spacer(modifier = Modifier.height(16.dp))
        GroupInfoCardComposable(
            name = "Math Group",
            memberCount = "5 Students"
        )
    }

}