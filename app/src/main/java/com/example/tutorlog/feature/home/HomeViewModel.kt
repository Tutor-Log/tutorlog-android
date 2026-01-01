package com.example.tutorlog.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.usecase.RGetHomeScreenContentUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val getHomeScreenContentUseCase: RGetHomeScreenContentUseCase
) : ContainerHost<HomeScreenState, HomeScreenSideEffect>, ViewModel() {
    override val container: Container<HomeScreenState, HomeScreenSideEffect> = container(
        HomeScreenState()
    )

    init {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(
                        isLoading = true
                    )
                }
            }
            getHomeScreenContentUseCase.process(
                RGetHomeScreenContentUseCase.UCRequest(
                    userId = preferencesManager.getInt(LocalKey.USER_ID)
                )
            )
                .collect { result ->
                    when (result) {
                        is Either.Success -> {
                            intent {
                                reduce {
                                    state.copy(
                                        userName = result.data.userInfo.name,
                                        image = result.data.userInfo.iamge,
                                        isLoading = false
                                    )
                                }
                            }
                        }
                        is Either.Error -> {
                        }
                    }
                }
        }
    }


    fun bottomTabSelected(tabType: BottomBarTabTypes) {
        when (tabType) {
            BottomBarTabTypes.HOME -> {

            }

            BottomBarTabTypes.STUDENTS -> {
                intent {
                    postSideEffect(HomeScreenSideEffect.NavigateToStudentsScreen)
                }
            }

            BottomBarTabTypes.EVENTS -> {

            }

            BottomBarTabTypes.MORE -> {

            }
        }
    }
}