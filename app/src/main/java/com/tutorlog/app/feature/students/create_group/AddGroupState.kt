package com.tutorlog.app.feature.students.create_group

data class AddGroupState(
    val isButtonLoading: Boolean = false,
    val groupName: String = "",
    val groupDescription: String = ""
)
