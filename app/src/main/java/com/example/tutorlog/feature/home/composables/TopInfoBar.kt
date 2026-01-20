package com.example.tutorlog.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.LocalTypography

@Composable
fun TopInfoBarComposable(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = "",
            modifier = Modifier.size(48.dp)
                .clip(shape = CircleShape)
                .background(color = LocalColors.Gray200)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Hi, $name",
            color = LocalColors.White,
            style = LocalTypography.headingMedium16
        )

    }
}

@Preview
@Composable
private fun TopInfoBarComposablePreview() {
    TopInfoBarComposable(
        imageUrl = "https://example.com/image.svg",
        modifier = Modifier.fillMaxWidth()
            .background(LocalColors.Red100),
        name = "John Doe"
    )
}