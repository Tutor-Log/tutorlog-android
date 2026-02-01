package com.example.tutorlog.feature.event_detail

data class EventDetailNavArgs(
    val eventId: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String
)
