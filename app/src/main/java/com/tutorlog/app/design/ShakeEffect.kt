package com.tutorlog.app.design

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun Modifier.shake(
    isShaking: Boolean,
    shakeStrength: Float = 25f
): Modifier {
    var triggerShake by remember { mutableStateOf(false) }

    val shake = remember(150) {
        keyframes {
            durationMillis = 150
            0f at 0
            -shakeStrength at 50
            shakeStrength at 100
            0f at 150
        }
    }

    val animatedShake by animateFloatAsState(
        targetValue = if (triggerShake) 1f else 0f,
        animationSpec = shake,
        finishedListener = { triggerShake = false },
        label = ""
    )

    LaunchedEffect(isShaking) {
        if (isShaking) {
            triggerShake = true
        }
    }

    return this.graphicsLayer {
        translationX = animatedShake
    }
}