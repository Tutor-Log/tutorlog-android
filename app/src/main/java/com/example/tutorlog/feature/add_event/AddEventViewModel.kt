package com.example.tutorlog.feature.add_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.EventFrequencyType
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RGetStudentGroupUseCase
import com.example.tutorlog.domain.usecase.base.Either
import com.example.tutorlog.utils.convertToDdMmmYy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val getStudentGroupUseCase: RGetStudentGroupUseCase
) : ContainerHost<AddEventState, AddEventSideEffect>, ViewModel() {

    override val container: Container<AddEventState, AddEventSideEffect> =
        container(initialState = AddEventState())

    init {
        getAddEventPupilList()
    }

    fun getAddEventPupilList() {
        viewModelScope.launch {
            intent {
                reduce {
                    state.copy(uiState = UIState.LOADING)
                }
            }
            getStudentGroupUseCase.process(
                request = RGetStudentGroupUseCase.UCRequest
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    uiState = UIState.SUCCESS,
                                    pupilList = result.data.pupilList.map { pupil ->
                                        UIAdditionPupil(
                                            id = pupil.id,
                                            name = pupil.fullName,
                                            details = pupil.email,
                                            isSelected = false
                                        )
                                    },
                                )
                            }
                        }
                    }

                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(uiState = UIState.ERROR)
                            }
                        }
                    }
                }
            }
        }
    }

    fun togglePupilSelection(pupilId: Int) = intent {
        val updatedList = state.pupilList.map { pupil ->
            if (pupil.id == pupilId) {
                pupil.copy(isSelected = !pupil.isSelected)
            } else {
                pupil
            }
        }
        reduce { state.copy(pupilList = updatedList) }
    }

    fun selectAllPupils() = intent {
        val allSelected = state.pupilList.all { it.isSelected }
        val updatedList = state.pupilList.map { pupil ->
            pupil.copy(isSelected = !allSelected)
        }
        reduce { state.copy(pupilList = updatedList) }
    }

    fun onEventNameEntered(eventName: String) {
        intent {
            reduce { 
                state.copy(
                    eventName = eventName,
                    showTitleError = if (eventName.isNotEmpty()) false else state.showTitleError
                ) 
            }
        }
    }

    fun onDescriptionEntered(description: String) {
        intent {
            reduce { state.copy(eventDescription = description) }
        }
    }

    fun onStartDateClick(date: Long) {
        intent {
            reduce {
                state.copy(
                    date = date.convertToDdMmmYy()
                )
            }
        }
    }

    fun updateStartTime(time: String) {
        intent {
            reduce {
                state.copy(
                    startTime = time,
                    showStartTimeError = false
                )
            }
        }
    }

    fun updateEndTime(time: String) {
        intent {
            reduce {
                state.copy(
                    endTime = time,
                    showEndTimeError = false
                )
            }
        }
    }

    fun updateFrequency(frequency: EventFrequencyType) {
        intent {
            reduce {
                state.copy(
                    frequency = if (frequency == EventFrequencyType.ONE_TIME) EventFrequencyType.REPEAT else EventFrequencyType.ONE_TIME
                )
            }
        }
    }

    fun updateSelectedDays(selectedList: List<Int>,day: Int) {
        val updatedList = selectedList.toMutableList()
        if (day !in selectedList) {
            updatedList.add(day)
        } else {
            updatedList.remove(day)
        }
        intent {
            reduce {
                state.copy(
                    selectedDays = updatedList,
                    showRepeatDaysError = false
                )
            }
        }
    }

    fun updateRepeatUntil(date: Long) {
        intent {
            reduce {
                state.copy(
                    repeatUntil = date.convertToDdMmmYy(),
                    showRepeatUntilError = false
                )
            }
        }
    }

    fun onSubmit(
        title: String,
        description: String,
        eventType: EventFrequencyType,
        date: String,
        startTime: String,
        endTime: String,
        repeatUntil: String,
        repeatDays: List<Int>,
        selectedPupils: List<UIAdditionPupil>
    ) {
        intent {
            var titleError = false
            var startTimeError = false
            var endTimeError = false
            var repeatDaysError = false
            var repeatUntilError = false
            var hasError = false

            if (title.isBlank()) {
                titleError = true
                hasError = true
            }
            if (startTime.isBlank()) {
                startTimeError = true
                hasError = true
            }
            if (endTime.isBlank()) {
                endTimeError = true
                hasError = true
            }

            if (eventType == EventFrequencyType.REPEAT) {
                if (repeatDays.isEmpty()) {
                    repeatDaysError = true
                    hasError = true
                }
                if (repeatUntil.isBlank()) {
                    repeatUntilError = true
                    hasError = true
                }
            }

            if (hasError) {
                reduce {
                    state.copy(
                        showTitleError = titleError,
                        showStartTimeError = startTimeError,
                        showEndTimeError = endTimeError,
                        showRepeatDaysError = repeatDaysError,
                        showRepeatUntilError = repeatUntilError,
                    )
                }
            } else {
                reduce {
                    state.copy(
                        showTitleError = false,
                        showStartTimeError = false,
                        showEndTimeError = false,
                        showRepeatDaysError = false,
                        showRepeatUntilError = false
                    )
                }
                // All mandatory fields are filled, proceed with submit
                // repeat_pattern -> weekly always
                println("karl title: $title")
                println("karl description: $description")
                println("karl eventType: ${if (eventType == EventFrequencyType.ONE_TIME) "once" else "REPEAT" }")
                println("karl date: ${convertToIsoDate(date)}")
                println("karl startTime: $startTime")
                println("karl endTime: $endTime")
                println("karl repeatUntil: ${convertToIsoDate(repeatUntil)}")
                println("karl repeatDays: $repeatDays")
                println("karl selectedPupils: $selectedPupils")
            }
        }
    }

    fun convertToIsoDate(dateString: String): String {
        try {
            val inputFormat = SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH)

            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            val date = inputFormat.parse(dateString)
            return outputFormat.format(date ?: Date())

        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

}