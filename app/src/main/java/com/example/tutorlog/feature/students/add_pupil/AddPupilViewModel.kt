package com.example.tutorlog.feature.students.add_pupil

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tutorlog.domain.local_storage.LocalKey
import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.domain.usecase.RAddPupilUseCase
import com.example.tutorlog.domain.usecase.base.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddPupilViewModel @Inject constructor(
    private val addPupilUseCase: RAddPupilUseCase,
    private val preferencesManager: PreferencesManager,
): ContainerHost<AddPupilState, AddPupilSideEffect>, ViewModel() {

    override val container: Container<AddPupilState, AddPupilSideEffect> = container(initialState = AddPupilState())

    init {

    }

    fun addPupil(
        name: String,
        email: String,
        phone: String,
        group: String?
    ) {
        intent {
            reduce {
                state.copy(
                    isButtonLoading = true
                )
            }
        }
        viewModelScope.launch {
            addPupilUseCase.process(
                request = RAddPupilUseCase.UCRequest(
                    userId = preferencesManager.getInt(LocalKey.USER_ID),
                    name = name,
                    email = email,
                    phone = phone,
                    group = group
                )
            ).collect { result ->
                when(result) {
                    is Either.Success -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(AddPupilSideEffect.ShowToast(message = "$name Added"))
                            postSideEffect(AddPupilSideEffect.NavigateToStudentScreen)
                        }
                    }
                    is Either.Error -> {
                        intent {
                            reduce {
                                state.copy(
                                    isButtonLoading = false
                                )
                            }
                            postSideEffect(AddPupilSideEffect.ShowToast(message = "Something went wrong"))
                        }
                    }
                }

            }
        }
    }

    fun onNameChange(name: String) {
        intent {
            reduce {
                state.copy(
                    name = name
                )
            }
        }
    }

    fun onEmailChange(email: String) {
        intent {
            reduce {
                state.copy(
                    email = email
                )
            }
        }
    }

    fun onPhoneChange(phone: String) {
        intent {
            reduce {
                state.copy(
                    phone = phone
                )
            }
        }
    }

    fun onDropDownClick(option: String) {
        intent {
            reduce {
                state.copy(
                    selectedGroup = option
                )
            }
        }
    }
}