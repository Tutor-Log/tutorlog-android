package com.example.tutorlog.domain.model.local

data class UIPupilInfo(
    val createdAt: String,
    val dateOfBirth: String,
    val email: String,
    val enrolledOn: String,
    val fatherName: String,
    val fullName: String,
    val gender: String,
    val id: Int,
    val mobile: String,
    val motherName: String,
    val ownerId: Int,
    val updatedAt: String
) {
    companion object {
        val EMPTY = UIPupilInfo(
            createdAt = "",
            dateOfBirth = "",
            email = "",
            enrolledOn = "",
            fatherName = "",
            fullName = "",
            gender = "",
            id = 0,
            mobile = "",
            motherName = "",
            ownerId = 0,
            updatedAt = ""
        )
    }
}
