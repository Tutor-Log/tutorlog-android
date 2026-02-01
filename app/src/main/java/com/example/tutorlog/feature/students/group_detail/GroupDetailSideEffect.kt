package com.example.tutorlog.feature.students.group_detail

interface GroupDetailSideEffect {
    data class NavigateToAddPupilToGroupScreen(
        val groupId: Int
    ): GroupDetailSideEffect

}