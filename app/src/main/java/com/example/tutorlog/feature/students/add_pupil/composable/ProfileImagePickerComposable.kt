package com.example.tutorlog.feature.students.add_pupil.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.tutorlog.design.LocalColors

@Composable
fun ProfileImagePickerComposable(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.size(112.dp)) { // 28 * 4 = 112dp
        // Dashed Circle Container
        Box(
            modifier = Modifier
                .size(112.dp)
                .clip(CircleShape)
                .background(LocalColors.Gray800)
                .clickable { /* Select Image logic */ },
            contentAlignment = Alignment.Center
        ) {
            // Draw Dashed Border manually
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
                drawCircle(color = Color.Gray, style = stroke)
            }

            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }

        // Camera Icon Overlay
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(36.dp)
                .border(1.dp, LocalColors.PrimaryGreen, CircleShape) // Ring effect
                .clip(CircleShape)
                .background(LocalColors.PrimaryGreen),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.AddCircle,
                contentDescription = "Upload",
                tint = Color.Black,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}