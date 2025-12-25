package com.example.tutorlog.domain.remote

data class UserInfoResponse(
    val created_at: String?,
    val email: String?,
    val full_name: String?,
    val google_user_id: String?,
    val id: Int?,
    val last_login_at: String?,
    val profile_pic_url: String?
)