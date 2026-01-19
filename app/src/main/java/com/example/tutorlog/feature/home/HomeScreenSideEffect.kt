package com.example.tutorlog.feature.home

sealed interface HomeScreenSideEffect {

    data object NavigateToStudentsScreen : HomeScreenSideEffect

    data object NavigateToAddEventScreen: HomeScreenSideEffect

    data class NavigateToEventDetail(
        val eventId: Int
    ): HomeScreenSideEffect

    data class ShowToast(val message: String): HomeScreenSideEffect

}