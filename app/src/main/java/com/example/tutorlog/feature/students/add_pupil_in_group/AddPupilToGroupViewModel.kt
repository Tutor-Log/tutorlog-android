package com.example.tutorlog.feature.students.add_pupil_in_group

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.domain.usecase.RAddPupilToGroupUseCase
import com.example.tutorlog.domain.usecase.RGetGroupDetailUseCase
import com.example.tutorlog.domain.usecase.RGetStudentGroupUseCase
import com.example.tutorlog.domain.usecase.base.Either
import com.ramcosta.composedestinations.generated.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddPupilToGroupViewModel @Inject constructor(
    private val getGroupDetailUseCase: RGetGroupDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getStudentGroupUseCase: RGetStudentGroupUseCase,
    private val addPupilToGroupUseCase: RAddPupilToGroupUseCase
) : ContainerHost<AddPupilToGroupState, AddPupilToGroupSideEffect>, ViewModel() {

    override val container: Container<AddPupilToGroupState, AddPupilToGroupSideEffect> = container(
        initialState = AddPupilToGroupState()
    )

    init {
        getAllAddPupilToGroupData()
    }

    fun getAllAddPupilToGroupData() {
        getStudentData()
        getAllPupil()
    }

    fun getAllPupil() {
        intent {
            reduce {
                state.copy(
                    uiState = UIState.LOADING
                )
            }
        }
        viewModelScope.launch {
            getStudentGroupUseCase.process(
                request = RGetStudentGroupUseCase.UCRequest
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    pupilList = result.data.pupilList.map {
                                        UIAdditionPupil(
                                            id = it.id,
                                            name = it.fullName,
                                            details = it.email,
                                            isSelected = false
                                        )
                                    }
                                )
                            }
                        }

                    }

                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    uiState = UIState.ERROR,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun getStudentData() {
        intent {
            reduce {
                state.copy(
                    uiState = UIState.LOADING
                )
            }
        }
        viewModelScope.launch {
            getGroupDetailUseCase.process(
                request = RGetGroupDetailUseCase.UCRequest(
                    groupId = savedStateHandle.navArgs<AddPupilToGroupNavArgs>().groupId
                )
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    uiState = UIState.SUCCESS,
                                    groupName = result.data.groupName,
                                    groupDescription = result.data.groupDescription,
                                )
                            }
                        }

                    }

                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    uiState = UIState.ERROR,
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    fun pupilToggled(pupilId: Int, pupilList: List<UIAdditionPupil>) {
        val list = pupilList.map { item ->
            if (item.id == pupilId) {
                if (item.isSelected == true) {
                    item.copy(isSelected = false)
                } else {
                    item.copy(isSelected = true)
                }
            } else {
                item
            }
        }
        intent {
            reduce {
                state.copy(
                    pupilList = list
                )
            }
        }
    }


    fun addSelectedPupilToGroup(list: List<UIAdditionPupil>) {
        intent {
            reduce {
                state.copy(
                    isButtonLoading = true
                )
            }
        }

        viewModelScope.launch {

            addPupilToGroupUseCase.process(
                request = RAddPupilToGroupUseCase.UCRequest(
                    pupilIdList = list.filter { item ->
                        item.isSelected
                    }.map {
                        it.id
                    },
                    groupId = savedStateHandle.navArgs<AddPupilToGroupNavArgs>().groupId
                )
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(
                                AddPupilToGroupSideEffect.ShowToast(
                                    message = "Pupil added successfully"
                                )
                            )
                            postSideEffect(AddPupilToGroupSideEffect.NavigateToGroupDetailScreen(
                                groupId = savedStateHandle.navArgs<AddPupilToGroupNavArgs>().groupId
                            ))
                        }
                    }

                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(
                                AddPupilToGroupSideEffect.ShowToast(
                                    message = "Something went wrong"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}