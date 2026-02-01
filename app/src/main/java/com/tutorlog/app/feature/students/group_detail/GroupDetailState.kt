package com.tutorlog.app.feature.students.group_detail

import com.tutorlog.app.domain.model.local.UIPupilInfo
import com.tutorlog.app.domain.types.UIState

data class GroupDetailState(
    val screenState: UIState = UIState.NONE,
    val groupName: String = "",
    val groupDescription: String = "",
    val pupilList: List<UIPupilInfo> = emptyList(),
)
