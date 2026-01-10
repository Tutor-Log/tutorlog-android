package com.example.tutorlog.feature.students.add_pupil_in_group

import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.UIState

data class AddPupilToGroupState(
    val uiState: UIState = UIState.NONE,
    val groupName: String = "",
    val groupDescription: String = "",
    val pupilList: List<UIAdditionPupil> = emptyList(),
    val allPupilList: List<UIAdditionPupil> = emptyList(),
    val addedPupilList: List<UIAdditionPupil> = emptyList(),
    val isButtonLoading: Boolean = false
)
