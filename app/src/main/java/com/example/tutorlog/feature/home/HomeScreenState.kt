package com.example.tutorlog.feature.home

import com.example.tutorlog.domain.model.local.UIDateInfo
import com.example.tutorlog.domain.model.local.UIPupilClassInfo
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.types.UIState

data class HomeScreenState(
    val uiState: UIState = UIState.NONE,
    val selectedDateIndex:Int = 0,
    val image: String = "",
    val userName: String = "",
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.HOME,
    val pupilList: List<UIPupilClassInfo> = listOf(
        UIPupilClassInfo(
            isRepeat = true,
            time = "09:00",
            meridiem = "AM",
            title = "Jane Doe",
            subtitle = "Violin • Grade 3",
            description = "Reviewing Vivaldi Concerto in A Minor, 1st Mvt."
        ),
        UIPupilClassInfo(
            isRepeat = false,
            time = "10:30",
            meridiem = "AM",
            title = "Advanced Quartet",
            subtitle = "Ensemble Rehearsal",
            description = "Group practice for the upcoming Winter Recital."
        ),
        UIPupilClassInfo(
            isRepeat = true,
            time = "02:15",
            meridiem = "PM",
            title = "John Smith",
            subtitle = "Piano • Beginner",
            description = "Suzuki Book 1: Twinkle Variations and hand positioning."
        ),
        UIPupilClassInfo(
            isRepeat = false,
            time = "04:00",
            meridiem = "PM",
            title = "Sarah Connor",
            subtitle = "Music Theory • Level 2",
            description = "Focusing on circle of fifths and minor scales."
        ),
        UIPupilClassInfo(
            isRepeat = true,
            time = "05:45",
            meridiem = "PM",
            title = "Michael Chang",
            subtitle = "Cello • Grade 5",
            description = "Working on bowing techniques and vibrato consistency."
        )
    ),
    val dateList: List<UIDateInfo> = emptyList()
)
