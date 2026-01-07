package com.example.tutorlog.feature.students.create_pupil

data class AddPupilState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val selectedGroup: String = "None (Individual Lesson)",
    val groupOptionList: List<String> = listOf("Advanced Violins", "Beginner's Quartet", "Intermediate Ensemble", "None (Individual Lesson)"),
    val isButtonLoading: Boolean = false
)
