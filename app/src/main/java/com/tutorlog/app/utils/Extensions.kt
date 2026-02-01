package com.tutorlog.app.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String?.getInitials(limit: Int = 2): String {
    if (this.isNullOrBlank()) return ""

    return this.trim()
        .split("\\s+".toRegex()) // Split by whitespace (handles multiple spaces)
        .filter { it.isNotEmpty() }
        .take(limit) // Take first N parts (usually 2)
        .joinToString("") { it.first().uppercase() }
}

fun Long.convertMillisToyyyyMMdd(): String {
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