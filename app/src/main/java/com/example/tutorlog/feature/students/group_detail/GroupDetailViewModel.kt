package com.example.tutorlog.feature.students.group_detail

import androidx.lifecycle.ViewModel
import com.example.tutorlog.domain.types.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(): ContainerHost<GroupDetailState, GroupDetailSideEffect>, ViewModel() {

    override val container: Container<GroupDetailState, GroupDetailSideEffect> = container(
        initialState = GroupDetailState()
    )

    init {
        getGroupDetail()

    }

    fun getGroupDetail() {
        intent {
            reduce {
                state.copy(
                    screenState = UIState.SUCCESS
                )
            }
        }
    }
}