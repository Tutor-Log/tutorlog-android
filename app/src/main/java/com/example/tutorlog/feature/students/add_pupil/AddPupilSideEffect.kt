package com.example.tutorlog.feature.students.add_pupil

sealed interface AddPupilSideEffect {
    data object NavigateToStudentScreen: AddPupilSideEffect
    data class ShowToast(val message: String): AddPupilSideEffect
}