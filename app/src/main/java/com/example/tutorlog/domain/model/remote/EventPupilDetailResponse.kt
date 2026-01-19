package com.example.tutorlog.domain.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class EventPupilDetailResponse(
    val added_at: String?,
    val event_id: Int?,
    val id: Int?,
    val pupil: GetPupilResponse?,
    val pupil_id: Int?
)