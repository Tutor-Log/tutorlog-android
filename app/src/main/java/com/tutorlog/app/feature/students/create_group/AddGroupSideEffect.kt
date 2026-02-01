package com.tutorlog.app.feature.students.create_group

interface AddGroupSideEffect {

    data object NavigateToStudentScreen: AddGroupSideEffect

    data class ShowToast(val message: String): AddGroupSideEffect
}