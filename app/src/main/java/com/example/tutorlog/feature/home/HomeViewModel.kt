package com.example.tutorlog.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.usecase.Either
import com.example.tutorlog.domain.usecase.RGetHomeScreenContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.tutorlog.domain.usecase.Result
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeScreenContentUseCase: RGetHomeScreenContentUseCase
) : ContainerHost<HomeScreenState, HomeScreenSideEffect>, ViewModel() {
    override val container: Container<HomeScreenState, HomeScreenSideEffect> = container(
        HomeScreenState()
    )

    init {
        viewModelScope.launch {
            getHomeScreenContentUseCase.process(RGetHomeScreenContentUseCase.UCRequest())
                .collect { result ->
                    when (result) {
                        is Either.Success -> {
                            intent {
                                reduce {
                                    state.copy(
                                        userName = result.data.userList.firstOrNull {
                                            it.displayName.contains("Sam")
                                        }?.displayName.orEmpty(),
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