package com.example.tutorlog.domain.remote

data class CreateUserPostBody(
    val google_user_id: String?,
    val email: String?,
    val full_name: String?,
    val profile_pic_url: String?,
)
