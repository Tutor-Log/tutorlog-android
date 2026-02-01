package com.tutorlog.app.feature.event_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorlog.app.domain.types.UIState
import com.tutorlog.app.domain.usecase.RDeleteEventUseCase
import com.tutorlog.app.domain.usecase.RGetEventDetailUseCase
import com.tutorlog.app.domain.usecase.base.Either
import com.ramcosta.composedestinations.generated.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val getEventPupilList: RGetEventDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val deleteEventUseCase: RDeleteEventUseCase
) :
    ContainerHost<EventDetailState, EventDetailSideEffect>, ViewModel() {

    override val container: Container<EventDetailState, EventDetailSideEffect> =
        container(initialState = EventDetailState())


    init {
        getEventDetail()
    }

    fun getEventDetail() {
        intent {
            reduce {
                state.copy(
                    uiState = UIState.LOADING,
                    title = savedStateHandle.navArgs<EventDetailNavArgs>().title,
                    description = savedStateHandle.navArgs<EventDetailNavArgs>().description,
                    date = savedStateHandle.navArgs<EventDetailNavArgs>().date,
                    time = savedStateHandle.navArgs<EventDetailNavArgs>().time
                )
            }
        }

        viewModelScope.launch {
            getEventPupilList.process(
                request = RGetEventDetailUseCase.UCRequest(
                    eventId = savedStateHandle.navArgs<EventDetailNavArgs>().eventId
                )
            ).collect {
                when(it) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    pupilList = it.data.pupilList,
                                    uiState = UIState.SUCCESS
                                )
                            }
                        }
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

    fun onDeleteEvent(
        eventId: Int
    ) {
        intent {
            reduce {
                state.copy(
                    isButtonLoading = true
                )
            }
        }
        viewModelScope.launch {
            deleteEventUseCase.process(
                request = RDeleteEventUseCase.UCRequest(
                    eventId = eventId
                )
            ).collect {
                when(it) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(EventDetailSideEffect.ShowToast("Event deleted"))
                        }
                    }
                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(EventDetailSideEffect.ShowToast("Something went wrong"))
                        }
                    }
                }
            }
        }
    }
}