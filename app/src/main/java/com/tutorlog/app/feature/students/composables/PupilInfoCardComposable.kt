package com.tutorlog.app.feature.students.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.design.LocalTypography
import com.tutorlog.app.utils.getInitials

@Composable
fun PupilInfoCardComposable(
    name: String,
    phoneNumber: String,
    gender: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xff1f2937),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(
                        color = if (gender == "F") LocalColors.Red100 else LocalColors.Blue100,
                        shape = CircleShape
                    )
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.first().toString(),
                    color = LocalColors.Black700,
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
                    text = "+91-$phoneNumber",
                    color = LocalColors.Neutral300,
                    style = LocalTypography.bodySmall12
                )
            }
        }
    }

}

@Composable
fun GroupInfoCardComposable(
    name: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
                onClick = {
                    onClick.invoke()
                }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(
                    color = LocalColors.Red300, shape = RoundedCornerShape(
                        topStart = 16.dp,
                        bottomStart = 16.dp
                    )
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff1f2937),
                    shape = RoundedCornerShape(
                        bottomEnd = 16.dp,
                        topEnd = 16.dp
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
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.getInitials(),
                        color = LocalColors.Red300,
                        style = LocalTypography.headingSmall14
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = name,
                        color = LocalColors.White,
                        style = LocalTypography.headingSmall14
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        color = LocalColors.Neutral300,
                        style = LocalTypography.bodySmall12,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
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
            phoneNumber = "+1234567890",
            gender = "M"
        )
        Spacer(modifier = Modifier.height(16.dp))
        PupilInfoCardComposable(
            name = "John Doe",
            phoneNumber = "+1234567890",
            gender = "F"
        )
        Spacer(modifier = Modifier.height(16.dp))
        GroupInfoCardComposable(
            name = "Math Group",
            description = "5 Students",
            onClick = {

            },
        )
    }

}