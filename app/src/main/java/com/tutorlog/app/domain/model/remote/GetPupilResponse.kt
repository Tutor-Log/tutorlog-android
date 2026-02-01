package com.tutorlog.app.domain.model.remote

import kotlinx.serialization.Serializable

@Serializable
data class GetPupilResponse(
    val created_at: String?,
    val date_of_birth: String?,
    val email: String?,
    val enrolled_on: String?,
    val father_name: String?,
    val full_name: String?,
    val gender: String?,
    val id: Int?,
    val mobile: String?,
    val mother_name: String?,
    val owner_id: Int?,
    val updated_at: String?
)