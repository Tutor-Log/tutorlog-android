package com.example.tutorlog.feature.landing

sealed interface LandingSideEffect {

    data object NavigateToLoginScreen: LandingSideEffect
    data object NavigateToHomeScreen: LandingSideEffect
}