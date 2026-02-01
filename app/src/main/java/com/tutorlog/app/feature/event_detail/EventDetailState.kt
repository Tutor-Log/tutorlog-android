package com.tutorlog.app.feature.event_detail

import com.tutorlog.app.domain.model.local.UIPupilInfo
import com.tutorlog.app.domain.types.UIState

data class EventDetailState(
    val uiState: UIState = UIState.NONE,
    val pupilList: List<UIPupilInfo> = emptyList(),
    val isButtonLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = ""
)
