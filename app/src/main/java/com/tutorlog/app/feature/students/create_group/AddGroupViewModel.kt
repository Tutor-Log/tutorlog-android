package com.tutorlog.app.feature.students.create_group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorlog.app.domain.usecase.RCreateGroupUseCase
import com.tutorlog.app.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddGroupViewModel @Inject constructor(
    private val createGroupUseCase: RCreateGroupUseCase,
) : ContainerHost<AddGroupState, AddGroupSideEffect>, ViewModel() {

    override val container: Container<AddGroupState, AddGroupSideEffect> = container(
        initialState = AddGroupState()
    )


    fun onCreateGroup(
        name: String,
        description: String,
    ) {
        intent {
            reduce {
                state.copy(
                    isButtonLoading = true
                )
            }
        }
        viewModelScope.launch {
            createGroupUseCase.process(
                request = RCreateGroupUseCase.UCRequest(
                    name = name,
                    description = description
                )
            ).collect { result ->
                when (result) {
                    is Either.Success -> {
                        intent {
                            postSideEffect(AddGroupSideEffect.ShowToast(message = "Group created successfully"))
                            postSideEffect(AddGroupSideEffect.NavigateToStudentScreen)
                        }
                    }

                    is Either.Error -> {
                        intent {
                            postSideEffect(AddGroupSideEffect.ShowToast(message = "Something went wrong"))
                        }
                    }
                }

            }
        }
    }

    fun updateGroupName(value: String) {
        intent {
            reduce {
                state.copy(
                    groupName = value
                )
            }
        }
    }

    fun updateDescription(value: String) {
        intent {
            reduce {
                state.copy(
                    groupDescription = value
                )
            }
        }
    }
}