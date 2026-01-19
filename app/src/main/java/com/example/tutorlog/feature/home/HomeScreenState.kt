package com.example.tutorlog.feature.home

import com.example.tutorlog.domain.model.local.UIDateInfo
import com.example.tutorlog.domain.model.local.UIClassInfo
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.types.UIState

data class HomeScreenState(
    val uiState: UIState = UIState.NONE,
    val selectedDateIndex:Int = 0,
    val image: String = "",
    val userName: String = "",
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.HOME,
    val classList: List<UIClassInfo> = emptyList(),
    val dateList: List<UIDateInfo> = emptyList()
)
