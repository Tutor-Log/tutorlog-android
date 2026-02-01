package com.tutorlog.app.feature.home

import com.tutorlog.app.domain.model.local.UIDateInfo
import com.tutorlog.app.domain.model.local.UIClassInfo
import com.tutorlog.app.domain.types.BottomBarTabTypes
import com.tutorlog.app.domain.types.UIState

data class HomeScreenState(
    val uiState: UIState = UIState.NONE,
    val selectedDateIndex:Int = 0,
    val image: String = "",
    val userName: String = "",
    val selectedBottomTab: BottomBarTabTypes = BottomBarTabTypes.HOME,
    val classList: List<UIClassInfo> = emptyList(),
    val dateList: List<UIDateInfo> = emptyList(),
    val isEventLoading: Boolean = false,
    val currentDate: String = ""
)
