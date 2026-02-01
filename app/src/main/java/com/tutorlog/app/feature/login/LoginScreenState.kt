package com.tutorlog.app.feature.login

import com.tutorlog.app.domain.types.UIState

data class LoginScreenState(
    val uiState: UIState = UIState.NONE,
    val isLoading: Boolean = false,
)
