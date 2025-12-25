package com.example.tutorlog.feature.home

sealed class HomeScreenSideEffect {

    data object NavigateToStudentsScreen : HomeScreenSideEffect()
}