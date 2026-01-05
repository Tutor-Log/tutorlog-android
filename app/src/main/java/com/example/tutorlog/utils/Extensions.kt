package com.example.tutorlog.utils

fun String?.getInitials(limit: Int = 2): String {
    if (this.isNullOrBlank()) return ""

    return this.trim()
        .split("\\s+".toRegex()) // Split by whitespace (handles multiple spaces)
        .filter { it.isNotEmpty() }
        .take(limit) // Take first N parts (usually 2)
        .joinToString("") { it.first().uppercase() }
}