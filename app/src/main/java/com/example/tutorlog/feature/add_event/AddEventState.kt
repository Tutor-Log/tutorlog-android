package com.example.tutorlog.feature.add_event

import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.model.local.UIGroupInfo
import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.types.UIState

data class AddEventState(
    val uiState: UIState = UIState.NONE,
    val pupilList: List<UIPupilInfo> = emptyList(),
    val groupList: List<UIGroupInfo> = emptyList(),
    val selectablePupilList: List<UIAdditionPupil> = emptyList(),
    val selectableGroupList: List<UIAdditionGroup> = emptyList()
)
