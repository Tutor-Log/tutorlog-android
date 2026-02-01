package com.tutorlog.app.domain.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class EventData(
    val description: String?,
    val end_time: String?,
    val event_type: String?,
    val owner_id: Int?,
    val repeat_pattern: String?,
    val repeat_until: String?,
    val start_time: String?,
    val title: String?
)