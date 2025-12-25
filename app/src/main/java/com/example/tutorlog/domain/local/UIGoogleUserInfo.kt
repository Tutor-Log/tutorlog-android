package com.example.tutorlog.domain.local

data class UIGoogleUserInfo(
    val uid: String,
    val googleId: String,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
)
