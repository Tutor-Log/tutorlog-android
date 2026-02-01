package com.tutorlog.app.domain.model.remote

data class CreateGroupPostBody(
    val description: String?,
    val name: String?,
    val owner_id: Int?
)