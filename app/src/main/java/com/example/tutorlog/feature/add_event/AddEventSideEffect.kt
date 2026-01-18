package com.example.tutorlog.feature.add_event

interface AddEventSideEffect {

    data class ShowToast(val message: String): AddEventSideEffect
    data object NavigateToHomeScreen: AddEventSideEffect
}