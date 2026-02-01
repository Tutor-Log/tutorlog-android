package com.tutorlog.app.domain.model.remote

data class AddPupilToGroupResponseItem(
    val group_id: Int?,
    val id: Int?,
    val joined_at: String?,
    val pupil_id: Int?
)