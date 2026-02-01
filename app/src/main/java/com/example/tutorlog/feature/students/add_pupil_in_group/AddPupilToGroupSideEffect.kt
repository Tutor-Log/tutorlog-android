package com.example.tutorlog.feature.students.add_pupil_in_group

interface AddPupilToGroupSideEffect {

    data class ShowToast(
        val message: String
    ): AddPupilToGroupSideEffect

    data class NavigateToGroupDetailScreen(
        val groupId: Int
    ): AddPupilToGroupSideEffect

}