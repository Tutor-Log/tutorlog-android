package com.tutorlog.app.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.design.LocalTypography

@Composable
fun PupilClassTimingComposable(
    timing: String,
    image: String,
    name: String,
    level: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timing,
            style = LocalTypography.bodySmall12,
            color = LocalColors.Neutral200,
            modifier = Modifier.width(60.dp)
        )
        Spacer(
            modifier = Modifier.width(24.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff1c2620),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.dp,
                    color = LocalColors.Neutral700,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(56.dp)
                    .background(
                        color = LocalColors.Neutral70,
                        shape = CircleShape
                    )
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Pupil Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = name,
                    style = LocalTypography.headingSmall14,
                    color = LocalColors.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = level,
                    style = LocalTypography.bodySmall12,
                    color = LocalColors.Neutral400
                )
            }

        }

    }

}

@Preview
@Composable
private fun PreviewPupilClassTimingComposable() {
    PupilClassTimingComposable(
        timing = "08:00 AM",
        image = "",
        name = "John Doe",
        level = "Beginner"
    )
}