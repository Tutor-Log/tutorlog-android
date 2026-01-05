package com.example.tutorlog.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TFullScreenErrorComposable(
    onRetryClick: () -> Unit = {},
    onSupportClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LocalColors.BackgroundDefaultDark)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Error Icon Circle
            Surface(
                modifier = Modifier.size(96.dp), // w-24 h-24
                shape = CircleShape,
                color = LocalColors.Red500.copy(alpha = 0.1f) // bg-red-500/10
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Rounded.Warning,
                        contentDescription = "Connection Error",
                        tint = LocalColors.Red500,
                        modifier = Modifier.size(48.dp) // text-5xl approx
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp)) // mb-6

            // Title
            Text(
                text = "Connection Error",
                color = Color.White,
                fontSize = 24.sp, // text-2xl
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Description
            Text(
                text = "We couldn't connect to the server to fetch your data. Please check your internet connection and try again.",
                color = LocalColors.Gray400,
                fontSize = 16.sp, // text-base
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier
                    .widthIn(max = 320.dp) // max-w-xs
                    .padding(bottom = 32.dp) // mb-8
            )

            // Buttons Container
            Column(
                modifier = Modifier.widthIn(max = 320.dp), // max-w-xs
                verticalArrangement = Arrangement.spacedBy(12.dp) // space-y-3
            ) {
                // Retry Button
                Button(
                    onClick = onRetryClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColors.PrimaryGreen,
                        contentColor = LocalColors.Gray900
                    ),
                    shape = RoundedCornerShape(16.dp), // rounded-2xl
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp) // py-4
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Retry",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewConnectionError() {
    MaterialTheme {
        TFullScreenErrorComposable()
    }
}