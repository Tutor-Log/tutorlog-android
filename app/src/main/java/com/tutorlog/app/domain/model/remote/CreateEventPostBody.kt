package com.tutorlog.app.domain.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class CreateEventPostBody(
    val event_data: EventData?,
    val pupil_ids: List<Int?>?,
    val repeat_days: List<Int?>?
)