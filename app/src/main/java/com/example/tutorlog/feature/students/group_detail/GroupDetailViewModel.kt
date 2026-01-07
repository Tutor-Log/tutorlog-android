package com.example.tutorlog.feature.students.group_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RGetGroupDetailUseCase
import com.example.tutorlog.domain.usecase.base.Either
import com.ramcosta.composedestinations.generated.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val getGroupDetailUseCase: RGetGroupDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ContainerHost<GroupDetailState, GroupDetailSideEffect>, ViewModel() {

    override val container: Container<GroupDetailState, GroupDetailSideEffect> = container(
        initialState = GroupDetailState()
    )

    init {
        getGroupDetail()

    }

    fun getGroupDetail() {
        intent {
            reduce {
                state.copy(
                    screenState = UIState.LOADING
                )
            }
        }
        viewModelScope.launch {
            getGroupDetailUseCase.process(
                request = RGetGroupDetailUseCase.UCRequest(
                    groupId = savedStateHandle.navArgs<GroupDetailNavArgs>().groupId
                )
            ).collect { response ->
                when (response) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    screenState = UIState.SUCCESS,
                                    groupName = response.data.groupName,
                                    groupDescription = response.data.groupDescription,
                                    pupilList = response.data.pupilList,
                                    groupId = response.data.groupId
                                )
                            }
                        }
                    }

                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    screenState = UIState.ERROR,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}