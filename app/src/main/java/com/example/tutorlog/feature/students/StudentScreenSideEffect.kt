package com.example.tutorlog.feature.students

sealed interface StudentScreenSideEffect {
    data object NavigateToHomeScreen : StudentScreenSideEffect
}