package com.example.tutorlog.feature.add_event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.feature.add_event.composable.AddEventScreenComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>
@Composable
fun AddEventScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {

        }
    }
    InitializeAddEventScreen(
        selectablePupilList = state.selectablePupilList,
        selectableGroupList = state.selectableGroupList,
        onBackClick = { navigator.popBackStack() },
        onPupilToggled = { viewModel.togglePupilSelection(it) },
        onGroupToggled = { viewModel.toggleGroupSelection(it) },
        onSelectAllPupils = { viewModel.selectAllPupils() },
        onSelectAllGroups = { viewModel.selectAllGroups() }
    )

}

@Composable
private fun InitializeAddEventScreen(
    selectablePupilList: List<UIAdditionPupil>,
    selectableGroupList: List<UIAdditionGroup>,
    onBackClick: () -> Unit,
    onPupilToggled: (Int) -> Unit,
    onGroupToggled: (Int) -> Unit,
    onSelectAllPupils: () -> Unit,
    onSelectAllGroups: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) { contentPadding ->
        AddEventScreenComposable(
            selectablePupilList = selectablePupilList,
            selectableGroupList = selectableGroupList,
            onBackClick = onBackClick,
            onPupilToggled = onPupilToggled,
            onGroupToggled = onGroupToggled,
            onSelectAllPupils = onSelectAllPupils,
            onSelectAllGroups = onSelectAllGroups,
            modifier = Modifier
                .padding(contentPadding)
                .background(LocalColors.BackgroundDefaultDark)
                .fillMaxSize()
        )
    }
}