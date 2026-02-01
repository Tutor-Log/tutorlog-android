package com.tutorlog.app.feature.students.student_landing

import com.tutorlog.app.domain.model.local.UIGroupInfo
import com.tutorlog.app.domain.model.local.UIPupilInfo
import com.tutorlog.app.domain.types.UIState
import com.tutorlog.app.domain.types.BottomBarTabTypes

data class StudentScreenState(
    val name: String = "",
    val selectedIndex: Int = 0,
    val screenState: UIState = UIState.NONE,
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.STUDENTS,
    val studentList: List<UIPupilInfo> = emptyList(),
    val groupList: List<UIGroupInfo> = emptyList()
)
