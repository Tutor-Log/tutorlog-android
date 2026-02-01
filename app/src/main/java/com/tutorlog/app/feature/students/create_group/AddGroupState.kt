package com.tutorlog.app.feature.students.create_group

import com.tutorlog.app.domain.types.UIState

data class AddGroupState(
    val isButtonLoading: Boolean = false,
    val groupName: String = "",
    val groupDescription: String = ""
)
