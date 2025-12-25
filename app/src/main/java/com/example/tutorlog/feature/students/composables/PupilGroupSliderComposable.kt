package com.example.tutorlog.feature.students.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PupilGroupSliderComposable(
    selectedIndex: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xff1f2937),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(6.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.4f)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = {
                        onClick.invoke(0)
                    }
                )
                .background(
                    color = if (selectedIndex == 0) Color(0xff38e07b) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pupils",
                style = if (selectedIndex == 0) LocalTypography.headingMedium16 else LocalTypography.bodyMedium14,
                color = if (selectedIndex == 0) LocalColors.Black else LocalColors.White
            )
        }
        Box(
            modifier = Modifier
                .weight(0.4f)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = {
                        onClick.invoke(1)
                    }
                )
                .background(
                    color = if (selectedIndex == 1) Color(0xff38e07b) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Groups",
                style = if (selectedIndex == 1) LocalTypography.headingMedium16 else LocalTypography.bodyMedium14,
                color = if (selectedIndex == 1) LocalColors.Black else LocalColors.White
            )
        }
    }
}


@Preview
@Composable
private fun PreviewPupilGroupSliderComposable() {
    PupilGroupSliderComposable(
        selectedIndex = 0,
        onClick = {}
    )
}