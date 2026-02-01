package com.example.tutorlog.domain.model.remote

data class CreateGroupResponse(
    val created_at: String?,
    val description: String?,
    val id: Int?,
    val name: String?,
    val owner_id: Int?
)