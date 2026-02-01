package com.tutorlog.app.domain.model.remote

data class GetAllGroupMemberResponse(
    val pupil_id: Int?,
    val group_id: Int?,
    val pupil_details: GetPupilResponse?,
)
