package com.example.tutorlog.feature.students.student_landing

sealed interface StudentScreenSideEffect {
    data object NavigateToHomeScreen : StudentScreenSideEffect
    data object NavigateToAddPupil: StudentScreenSideEffect
    data object NavigateToAddGroup: StudentScreenSideEffect
    data class NavigateToGroupDetail(val groupId: Int): StudentScreenSideEffect
}