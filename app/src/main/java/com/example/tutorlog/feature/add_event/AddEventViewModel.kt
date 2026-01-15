package com.example.tutorlog.feature.add_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RGetStudentGroupUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val getStudentGroupUseCase: RGetStudentGroupUseCase
): ContainerHost<AddEventState, AddEventSideEffect> , ViewModel(){

    override val container: Container<AddEventState, AddEventSideEffect> = container(initialState = AddEventState())

    init {
        loadStudentGroupData()
    }

    private fun loadStudentGroupData() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(uiState = UIState.LOADING)
                }
            }
            getStudentGroupUseCase.process(
                request = RGetStudentGroupUseCase.UCRequest
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        val selectablePupils = result.data.pupilList.map { pupil ->
                            UIAdditionPupil(
                                id = pupil.id,
                                name = pupil.fullName,
                                details = pupil.email,
                                isSelected = false
                            )
                        }
                        val selectableGroups = result.data.groupList.map { group ->
                            UIAdditionGroup(
                                id = group.groupId,
                                name = group.name,
                                description = group.description,
                                isSelected = false
                            )
                        }
                        intent {
                            reduce {
                                state.copy(
                                    uiState = UIState.SUCCESS,
                                    pupilList = result.data.pupilList,
                                    groupList = result.data.groupList,
                                    selectablePupilList = selectablePupils,
                                    selectableGroupList = selectableGroups
                                )
                            }
                        }
                    }
                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(uiState = UIState.ERROR)
                            }
                        }
                    }
                }
            }
        }
    }

    fun togglePupilSelection(pupilId: Int) = intent {
        val updatedList = state.selectablePupilList.map { pupil ->
            if (pupil.id == pupilId) {
                pupil.copy(isSelected = !pupil.isSelected)
            } else {
                pupil
            }
        }
        reduce { state.copy(selectablePupilList = updatedList) }
    }

    fun toggleGroupSelection(groupId: Int) = intent {
        val updatedList = state.selectableGroupList.map { group ->
            if (group.id == groupId) {
                group.copy(isSelected = !group.isSelected)
            } else {
                group
            }
        }
        reduce { state.copy(selectableGroupList = updatedList) }
    }

    fun selectAllPupils() = intent {
        val allSelected = state.selectablePupilList.all { it.isSelected }
        val updatedList = state.selectablePupilList.map { pupil ->
            pupil.copy(isSelected = !allSelected)
        }
        reduce { state.copy(selectablePupilList = updatedList) }
    }

    fun selectAllGroups() = intent {
        val allSelected = state.selectableGroupList.all { it.isSelected }
        val updatedList = state.selectableGroupList.map { group ->
            group.copy(isSelected = !allSelected)
        }
        reduce { state.copy(selectableGroupList = updatedList) }
    }
}