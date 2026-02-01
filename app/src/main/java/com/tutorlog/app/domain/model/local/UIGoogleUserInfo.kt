package com.tutorlog.app.domain.model.local

data class UIGoogleUserInfo(
    val uid: String,
    val googleId: String,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
)
