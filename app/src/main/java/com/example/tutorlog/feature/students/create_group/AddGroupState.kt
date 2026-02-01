package com.example.tutorlog.feature.students.create_group

import com.example.tutorlog.domain.types.UIState

data class AddGroupState(
    val isButtonLoading: Boolean = false,
    val groupName: String = "",
    val groupDescription: String = ""
)
