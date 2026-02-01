package com.tutorlog.app.domain.model.local

data class UIClassInfo(
    val isRepeat: Boolean,
    val time: String,
    val meridiem: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val id: Int,
    val endTime: String
)
