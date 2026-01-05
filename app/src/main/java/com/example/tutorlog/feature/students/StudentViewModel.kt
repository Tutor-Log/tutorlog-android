package com.example.tutorlog.feature.students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.usecase.RGetStudentGroupUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class StudentViewModel @Inject constructor(
    private val getStudentGroupUseCase: RGetStudentGroupUseCase
): ContainerHost<StudentScreenState, StudentScreenSideEffect>, ViewModel() {
    override val container: Container<StudentScreenState, StudentScreenSideEffect> = container(
        StudentScreenState()
    )


    init {
        getStudentData()
    }

    fun getStudentData() {
        intent {
            reduce {
                state.copy(
                    screenState = UIState.LOADING
                )
            }
        }
        viewModelScope.launch {
            getStudentGroupUseCase.process(
                request = RGetStudentGroupUseCase.UCRequest
            ).collect { result ->
                when(result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    screenState = UIState.SUCCESS,
                                    studentList = result.data.pupilList,
                                    groupList = result.data.groupList
                                )
                            }
                        }
                    }
                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    screenState = UIState.ERROR,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun changeIndex(index: Int) {
        intent {
            reduce {
                state.copy(
                    selectedIndex = index
                )
            }
        }
    }


    fun bottomTabSelected(tabType: BottomBarTabTypes) {
        when (tabType) {
            BottomBarTabTypes.HOME -> {
                intent {
                    postSideEffect(StudentScreenSideEffect.NavigateToHomeScreen)
                }
            }
            BottomBarTabTypes.STUDENTS -> {
                intent {
                    postSideEffect(StudentScreenSideEffect.NavigateToHomeScreen)
                }
            }
            BottomBarTabTypes.EVENTS -> {

            }
            BottomBarTabTypes.MORE -> {

            }
        }
        intent {
            reduce {
                state.copy(
                    selectedBottomTab = tabType
                )
            }
        }
    }

    fun navigateToAddPupil() {
        intent {
            postSideEffect(StudentScreenSideEffect.NavigateToAddPupil)
        }
    }
    fun navigateToAddGroup() {
        intent {
            postSideEffect(StudentScreenSideEffect.NavigateToAddGroup)
        }
    }
}