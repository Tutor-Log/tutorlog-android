package com.example.tutorlog.feature.event_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RGetEventDetailUseCase
import com.example.tutorlog.domain.usecase.base.Either
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
                    uiState = UIState.LOADING
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
}