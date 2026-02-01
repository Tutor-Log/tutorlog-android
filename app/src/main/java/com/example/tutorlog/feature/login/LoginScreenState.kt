package com.example.tutorlog.feature.login

import com.example.tutorlog.domain.types.UIState

data class LoginScreenState(
    val uiState: UIState = UIState.NONE,
    val isLoading: Boolean = false,
)
