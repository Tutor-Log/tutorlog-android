package com.example.tutorlog.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.model.local.UIDateInfo
import com.example.tutorlog.domain.types.BottomBarTabTypes
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RGetEventsUseCase
import com.example.tutorlog.domain.usecase.RGetHomeScreenContentUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val getHomeScreenContentUseCase: RGetHomeScreenContentUseCase,
    private val getEventsUseCase: RGetEventsUseCase
) : ContainerHost<HomeScreenState, HomeScreenSideEffect>, ViewModel() {
    override val container: Container<HomeScreenState, HomeScreenSideEffect> = container(
        HomeScreenState()
    )

    init {

        getHomeScreenContent()
        getDateList()
    }

    fun getDateList() {
        intent {
            val zoneId = ZoneId.systemDefault()
            val today = LocalDate.now(zoneId)
            val endOfYear = today.withDayOfYear(today.lengthOfYear())

            val dateList = mutableListOf<UIDateInfo>()
            var currentDate = today

            while (!currentDate.isAfter(endOfYear)) {
                val millis = currentDate.atStartOfDay(zoneId).toInstant().toEpochMilli()

                dateList.add(
                    UIDateInfo(
                        date = millis.getDayNumber(),
                        day = millis.toDayName(),
                        isSelected = currentDate.isEqual(today),
                        dateInMillis = millis
                    )
                )
                currentDate = currentDate.plusDays(1)
            }

            reduce {
                state.copy(dateList = dateList)
            }
        }
    }

    fun getHomeScreenContent() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(
                        uiState = UIState.LOADING
                    )
                }
            }
            val userId = preferencesManager.getInt(LocalKey.USER_ID)
            getHomeScreenContentUseCase.process(
                RGetHomeScreenContentUseCase.UCRequest(
                    userId = userId
                )
            )
                .collect { result ->
                    when (result) {
                        is Either.Success -> {
                            intent {
                                reduce {
                                    state.copy(
                                        userName = result.data.userInfo.name,
                                        image = result.data.userInfo.iamge,
                                        uiState = UIState.SUCCESS
                                    )
                                }
                            }
                            // Fetch events after successful user info fetch
                            getEvents(userId)
                        }
                        is Either.Error -> {
                            intent {
                                reduce {
                                    state.copy(
                                        uiState = UIState.ERROR
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun getEvents(ownerId: Int) {
        viewModelScope.launch {
            getEventsUseCase.process(
                RGetEventsUseCase.UCRequest(
                    ownerId = ownerId
                )
            )
                .collect { result ->
                    when (result) {
                        is Either.Success -> {
                            intent {
                                reduce {
                                    state.copy(
                                        pupilList = result.data.events
                                    )
                                }
                            }
                        }
                        is Either.Error -> {
                            // Keep the dummy data if events fetch fails
                        }
                    }
                }
        }
    }


    fun bottomTabSelected(tabType: BottomBarTabTypes) {
        when (tabType) {
            BottomBarTabTypes.HOME -> {

            }

            BottomBarTabTypes.STUDENTS -> {
                intent {
                    postSideEffect(HomeScreenSideEffect.NavigateToStudentsScreen)
                }
            }

            BottomBarTabTypes.EVENTS -> {

            }

            BottomBarTabTypes.MORE -> {

            }
        }
    }

    fun navigateToAddEvent() {
        intent {
            postSideEffect(HomeScreenSideEffect.NavigateToAddEventScreen)
        }
    }
    fun Long.toDayName(): String {
        val formatter = SimpleDateFormat("EEE", Locale.getDefault())
        return formatter.format(Date(this))
    }
    fun Long.getDayNumber(): String {
        val formatter = SimpleDateFormat("dd", Locale.getDefault())
        return formatter.format(Date(this))
    }
}