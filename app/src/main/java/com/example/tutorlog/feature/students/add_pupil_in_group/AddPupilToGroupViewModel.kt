package com.example.tutorlog.feature.students.add_pupil_in_group

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddPupilToGroupViewModel @Inject constructor(): ContainerHost<AddPupilToGroupState, AddPupilToGroupSideEffect>, ViewModel() {

    override val container: Container<AddPupilToGroupState, AddPupilToGroupSideEffect> = container(
        initialState = AddPupilToGroupState()
    )

    init {

    }
}