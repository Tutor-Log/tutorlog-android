package com.example.tutorlog.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A modifier extension that adds a shake animation effect to any composable.
 * @param trigger A unique value that, when changed, triggers the shake animation.
 *                Pass a counter that increments on each validation failure to trigger the shake.
 * @param enabled Whether the shake should be triggered when the trigger value changes.
 */
fun Modifier.shake(trigger: Int, enabled: Boolean): Modifier = composed {
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(trigger) {
        if (enabled && trigger > 0) {
            for (i in 0..2) {
                offsetX.animateTo(
                    targetValue = 10f,
                    animationSpec = spring(stiffness = 5000f)
                )
                offsetX.animateTo(
                    targetValue = -10f,
                    animationSpec = spring(stiffness = 5000f)
                )
            }
            offsetX.animateTo(0f)
        }
    }

    this.graphicsLayer {
        translationX = offsetX.value
    }
}

fun String?.getInitials(limit: Int = 2): String {
    if (this.isNullOrBlank()) return ""

    return this.trim()
        .split("\\s+".toRegex()) // Split by whitespace (handles multiple spaces)
        .filter { it.isNotEmpty() }
        .take(limit) // Take first N parts (usually 2)
        .joinToString("") { it.first().uppercase() }
}

fun Long.convertMillisToDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(this))
}

fun Long.convertToDdMmmYy(): String {
    val formatter = SimpleDateFormat("dd-MMM-yy", Locale.getDefault())
    return formatter.format(Date(this))
}

fun Long.convertToFormattedTime(): String {
    // "hh" = 12-hour format, "mm" = minutes, "a" = AM/PM marker
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return formatter.format(Date(this))
}