package com.tutorlog.app.feature.home

sealed interface HomeScreenSideEffect {

    data object NavigateToStudentsScreen : HomeScreenSideEffect

    data object NavigateToAddEventScreen: HomeScreenSideEffect

    data object NavigateAddPupilScreen: HomeScreenSideEffect
    data object NavigateAddGroupScreen: HomeScreenSideEffect

    data class NavigateToEventDetail(
        val eventId: Int,
        val title: String,
        val description: String,
        val date: String,
        val time: String
    ): HomeScreenSideEffect

    data class ShowToast(val message: String): HomeScreenSideEffect

}