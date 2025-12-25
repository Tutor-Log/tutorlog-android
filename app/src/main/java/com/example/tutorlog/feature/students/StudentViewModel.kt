package com.example.tutorlog.feature.students

import androidx.lifecycle.ViewModel
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.feature.home.HomeScreenSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class StudentViewModel @Inject constructor(): ContainerHost<StudentScreenState, StudentScreenSideEffect>, ViewModel() {
    override val container: Container<StudentScreenState, StudentScreenSideEffect> = container(
        StudentScreenState()
    )


    init {
        println("karl : Student screen")
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
}