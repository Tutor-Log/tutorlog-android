package com.tutorlog.app.feature.add_event

import com.tutorlog.app.domain.model.local.UIAdditionPupil
import com.tutorlog.app.domain.types.EventFrequencyType
import com.tutorlog.app.domain.types.UIState

data class AddEventState(
    val uiState: UIState = UIState.NONE,
    val pupilList: List<UIAdditionPupil> = emptyList(),
    val eventName: String = "",
    val eventDescription: String = "",
    val date: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val frequency: EventFrequencyType = EventFrequencyType.ONE_TIME,
    val repeatDays: List<Int> = emptyList(),
    val repeatUntil: String = "",
    val selectedDays: List<Int> = emptyList(),
    // Validation error states
    val showTitleError: Boolean = false,
    val showStartTimeError: Boolean = false,
    val showEndTimeError: Boolean = false,
    val showRepeatDaysError: Boolean = false,
    val showRepeatUntilError: Boolean = false,
    val showStartDateError: Boolean = false,
    val isButtonLoading: Boolean = false
)
