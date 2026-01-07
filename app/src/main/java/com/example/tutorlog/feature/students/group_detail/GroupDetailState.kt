package com.example.tutorlog.feature.students.group_detail

import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.types.UIState

data class GroupDetailState(
    val screenState: UIState = UIState.NONE,
    val groupName: String = "",
    val groupDescription: String = "",
    val pupilList: List<UIPupilInfo> = emptyList(),
    val groupId: Int = 0
)
