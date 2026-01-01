package com.example.tutorlog.feature.home

import com.example.tutorlog.domain.local.UIDateInfo
import com.example.tutorlog.domain.local.UIPupilClassInfo
import com.example.tutorlog.domain.types.BottomBarTabTypes

data class HomeScreenState(
    val isLoading: Boolean = false,
    val selectedDateIndex:Int = 0,
    val image: String = "",
    val userName: String = "",
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.HOME,
    val pupilClassList: List<UIPupilClassInfo> = listOf(
        UIPupilClassInfo(
            timing = "08:00 AM",
            image = "",
            name = "Emma Thompson",
            level = "Beginner"
        ),
        UIPupilClassInfo(
            timing = "09:30 AM",
            image = "",
            name = "Liam Rodriguez",
            level = "Intermediate"
        ),
        UIPupilClassInfo(
            timing = "11:00 AM",
            image = "",
            name = "Sophia Chen",
            level = "Advanced"
        ),
        UIPupilClassInfo(
            timing = "01:15 PM",
            image = "",
            name = "Noah Williams",
            level = "Beginner"
        ),
        UIPupilClassInfo(
            timing = "02:45 PM",
            image = "",
            name = "Ava Patel",
            level = "Intermediate"
        ),
        UIPupilClassInfo(
            timing = "04:00 PM",
            image = "",
            name = "Mason Johnson",
            level = "Advanced"
        ),
        UIPupilClassInfo(
            timing = "05:30 PM",
            image = "",
            name = "Isabella Davis",
            level = "Beginner"
        ),
        UIPupilClassInfo(
            timing = "07:00 PM",
            image = "",
            name = "Oliver Kim",
            level = "Intermediate"
        )
    ),
    val dateInfoList: List<UIDateInfo> = listOf(
        UIDateInfo(date = "8", day = "Sun"),
        UIDateInfo(date = "9", day = "Mon"),
        UIDateInfo(date = "10", day = "Tue"),
        UIDateInfo(date = "11", day = "Wed"),
        UIDateInfo(date = "12", day = "Thu"),
        UIDateInfo(date = "13", day = "Fri"),
        UIDateInfo(date = "14", day = "Sat"),
        UIDateInfo(date = "15", day = "Sun"),
        UIDateInfo(date = "16", day = "Mon"),
        UIDateInfo(date = "17", day = "Tue"),
        UIDateInfo(date = "18", day = "Wed"),
        UIDateInfo(date = "19", day = "Thu"),
        UIDateInfo(date = "20", day = "Fri"),
        UIDateInfo(date = "21", day = "Sat"),
        UIDateInfo(date = "22", day = "Sun"),
        UIDateInfo(date = "23", day = "Mon"),
        UIDateInfo(date = "24", day = "Tue"),
        UIDateInfo(date = "25", day = "Wed"),
    ),
)
