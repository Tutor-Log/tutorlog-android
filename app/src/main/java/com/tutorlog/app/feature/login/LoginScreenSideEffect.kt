package com.tutorlog.app.feature.login

sealed interface LoginScreenSideEffect {
    data object SignInWithGoogle: LoginScreenSideEffect

    data object NavigateToHomeScreen: LoginScreenSideEffect

    data class ShowToast(val message: String): LoginScreenSideEffect

}