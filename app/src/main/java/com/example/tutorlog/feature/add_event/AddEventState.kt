package com.example.tutorlog.feature.add_event

import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.model.local.UIGroupInfo
import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.types.EventFrequencyType
import com.example.tutorlog.domain.types.UIState

data class AddEventState(
    val uiState: UIState = UIState.NONE,
    val pupilList: List<UIPupilInfo> = emptyList(),
    val selectablePupilList: List<UIAdditionPupil> = emptyList(),
    val eventName: String = "",
    val eventDescription: String = "",
    val selectedPupils: List<UIAdditionPupil> = emptyList(),
    val date: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val frequency: EventFrequencyType = EventFrequencyType.ONE_TIME,
    val repeatDays: List<Int> = emptyList(),
    val repeatUntil: String = ""
)
