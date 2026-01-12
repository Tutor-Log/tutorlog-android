package com.example.tutorlog.feature.add_event

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class AddEventViewModel @Inject constructor(): ContainerHost<AddEventState, AddEventSideEffect> , ViewModel(){

    override val container: Container<AddEventState, AddEventSideEffect> = container(initialState = AddEventState())

    init {

    }


}