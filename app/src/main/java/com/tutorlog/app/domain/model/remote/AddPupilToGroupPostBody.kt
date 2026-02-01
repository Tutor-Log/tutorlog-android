package com.tutorlog.app.domain.model.remote

data class AddPupilToGroupPostBody(
    val group_id: Int?,
    val pupil_id: Int?
)