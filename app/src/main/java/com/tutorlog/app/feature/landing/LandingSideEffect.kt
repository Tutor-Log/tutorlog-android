package com.tutorlog.app.feature.landing

sealed interface LandingSideEffect {

    data object NavigateToLoginScreen: LandingSideEffect
    data object NavigateToHomeScreen: LandingSideEffect
}