package com.example.tutorlog.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.local.UIGoogleUserInfo
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.local.UIUserInfo
import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.usecase.RCreateUserUseCase
import com.example.tutorlog.domain.usecase.RGetLoginUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: RGetLoginUseCase,
    private val createUserUseCase: RCreateUserUseCase,
    private val preferencesManager: PreferencesManager,
): ContainerHost<LoginScreenState, LoginScreenSideEffect>, ViewModel() {

    override val container: Container<LoginScreenState, LoginScreenSideEffect> = container(initialState = LoginScreenState())


    init {
    }

    fun createUser(userInfo: UIGoogleUserInfo) {
        viewModelScope.launch {
            createUserUseCase.process(request = RCreateUserUseCase.UCRequest(
                email = userInfo.email,
                name = userInfo.displayName,
                googleId = userInfo.googleId,
                image = userInfo.photoUrl
            )).collect { result ->
                when(result) {
                    is Either.Success -> {
                        intent {
                            postSideEffect(LoginScreenSideEffect.ShowToast(message = "Welcome ${result.data.userInfo.name}"))
                        }
                        saveLocalUser(
                            userInfo = UIUserInfo(
                                googleId = result.data.userInfo.googleId,
                                name = result.data.userInfo.name,
                                email = result.data.userInfo.email,
                                iamge = result.data.userInfo.iamge,
                                id = result.data.userInfo.id
                            )
                        )
                        intent {
                            postSideEffect(LoginScreenSideEffect.NavigateToHomeScreen)
                        }

                    }
                    is Either.Error -> {
                    }
                }
            }
        }
    }
    fun onSignIn() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(
                        isLoading = true
                    )
                }
                postSideEffect(LoginScreenSideEffect.SignInWithGoogle)
            }
        }
    }

    fun saveLocalUser(userInfo: UIUserInfo) {
        viewModelScope.launch {
            preferencesManager.saveString(
                key = LocalKey.NAME,
                value = userInfo.name
            )
            preferencesManager.saveString(
                key = LocalKey.EMAIL,
                value = userInfo.email
            )
            preferencesManager.saveString(
                key = LocalKey.GOOGLE_ID,
                value = userInfo.googleId
            )
            preferencesManager.saveString(
                key = LocalKey.IMAGE,
                value = userInfo.iamge
            )
            preferencesManager.saveInt(
                key = LocalKey.USER_ID,
                value = userInfo.id
            )
        }
    }

    fun onSignInCancel() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}