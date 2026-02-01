package com.tutorlog.app.domain.model.remote

data class AddPupilPostBody(
    val date_of_birth: String?,
    val email: String?,
    val enrolled_on: String?,
    val father_name: String?,
    val full_name: String?,
    val gender: String?,
    val mobile: String?,
    val mother_name: String?,
    val owner_id: Int?
)