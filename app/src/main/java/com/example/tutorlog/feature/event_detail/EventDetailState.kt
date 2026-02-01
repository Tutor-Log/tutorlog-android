package com.example.tutorlog.feature.event_detail

import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.types.UIState

data class EventDetailState(
    val uiState: UIState = UIState.NONE,
    val pupilList: List<UIPupilInfo> = emptyList(),
    val isButtonLoading: Boolean = false,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = ""
)
