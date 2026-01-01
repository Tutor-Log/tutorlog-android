package com.example.tutorlog.feature.students.add_pupil

import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AddPupilViewModel @Inject constructor(): ContainerHost<AddPupilState, AddPupilSideEffect>, ViewModel() {

    override val container: Container<AddPupilState, AddPupilSideEffect> = container(initialState = AddPupilState())

    init {

    }
}