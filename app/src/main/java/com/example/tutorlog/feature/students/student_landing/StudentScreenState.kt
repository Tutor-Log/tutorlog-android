package com.example.tutorlog.feature.students.student_landing

import com.example.tutorlog.domain.model.local.UIGroupInfo
import com.example.tutorlog.domain.model.local.UIPupilInfo
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.types.BottomBarTabTypes

data class StudentScreenState(
    val name: String = "",
    val selectedIndex: Int = 0,
    val screenState: UIState = UIState.NONE,
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.STUDENTS,
    val studentList: List<UIPupilInfo> = emptyList(),
    val groupList: List<UIGroupInfo> = emptyList()
)
