package com.example.tutorlog.feature.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.usecase.RCreateUserUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ContainerHost<LandingScreenState, LandingSideEffect>, ViewModel() {


    override val container: Container<LandingScreenState, LandingSideEffect> =
        container(initialState = LandingScreenState())

    init {
        checkoutUser()
    }

    private fun checkoutUser() {
        viewModelScope.launch {
            val name = preferencesManager.getString(LocalKey.NAME)
            val email = preferencesManager.getString(LocalKey.EMAIL)

            if (name.isNotEmpty() && email.isNotEmpty()) {
                intent {
                    delay(1500)
                    postSideEffect(LandingSideEffect.NavigateToHomeScreen)
                }
            } else {
                intent {
                    delay(1500)
                    postSideEffect(LandingSideEffect.NavigateToLoginScreen)
                }
            }
        }
    }
}