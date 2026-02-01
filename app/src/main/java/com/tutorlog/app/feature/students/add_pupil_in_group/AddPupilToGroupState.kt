package com.tutorlog.app.feature.students.add_pupil_in_group

import com.tutorlog.app.domain.model.local.UIAdditionPupil
import com.tutorlog.app.domain.types.UIState

data class AddPupilToGroupState(
    val uiState: UIState = UIState.NONE,
    val groupName: String = "",
    val groupDescription: String = "",
    val pupilList: List<UIAdditionPupil> = emptyList(),
    val isButtonLoading: Boolean = false
)
