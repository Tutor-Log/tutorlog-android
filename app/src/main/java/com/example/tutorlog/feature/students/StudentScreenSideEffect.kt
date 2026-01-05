package com.example.tutorlog.feature.students

sealed interface StudentScreenSideEffect {
    data object NavigateToHomeScreen : StudentScreenSideEffect
    data object NavigateToAddPupil: StudentScreenSideEffect

    data object NavigateToAddGroup: StudentScreenSideEffect


}