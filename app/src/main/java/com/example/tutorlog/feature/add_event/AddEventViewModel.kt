package com.example.tutorlog.feature.add_event

import androidx.lifecycle.ViewModel
import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AddEventViewModel @Inject constructor(): ContainerHost<AddEventState, AddEventSideEffect> , ViewModel(){

    override val container: Container<AddEventState, AddEventSideEffect> = container(initialState = AddEventState())

    init {
        loadMockData()
    }

    private fun loadMockData() = intent {
        val mockPupils = listOf(
            UIAdditionPupil(id = 1, name = "John Doe", details = "Grade 10, Class A", isSelected = false),
            UIAdditionPupil(id = 2, name = "Jane Smith", details = "Grade 9, Class B", isSelected = false),
            UIAdditionPupil(id = 3, name = "Alice Johnson", details = "Grade 10, Class A", isSelected = false),
            UIAdditionPupil(id = 4, name = "Bob Wilson", details = "Grade 8, Class C", isSelected = false),
            UIAdditionPupil(id = 5, name = "Emma Davis", details = "Grade 9, Class B", isSelected = false)
        )

        val mockGroups = listOf(
            UIAdditionGroup(id = 1, name = "Advanced Math", description = "5 students", isSelected = false),
            UIAdditionGroup(id = 2, name = "Beginner Piano", description = "3 students", isSelected = false),
            UIAdditionGroup(id = 3, name = "Science Club", description = "8 students", isSelected = false),
            UIAdditionGroup(id = 4, name = "Art Class", description = "4 students", isSelected = false)
        )

        reduce {
            state.copy(
                selectablePupilList = mockPupils,
                selectableGroupList = mockGroups
            )
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