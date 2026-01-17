package com.example.tutorlog.feature.add_event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.TFullScreenErrorComposable
import com.example.tutorlog.design.TFullScreenLoaderComposable
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.UIState
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

    when (state.uiState) {
        UIState.SUCCESS -> {
            InitializeAddEventScreen(
                selectablePupilList = state.selectablePupilList,
                onBackClick = { navigator.popBackStack() },
                onPupilToggled = { viewModel.togglePupilSelection(it) },
                onSelectAllPupils = { viewModel.selectAllPupils() },
            )
        }

        UIState.LOADING -> {
            TFullScreenLoaderComposable()
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getAddEventPupilList()
            }
        }

        UIState.NONE -> {}
    }
}

@Composable
private fun InitializeAddEventScreen(
    selectablePupilList: List<UIAdditionPupil>,
    onBackClick: () -> Unit,
    onPupilToggled: (Int) -> Unit,
    onSelectAllPupils: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
    ) { contentPadding ->
        AddEventScreenComposable(
            selectablePupilList = selectablePupilList,
            onBackClick = onBackClick,
            onPupilToggled = onPupilToggled,
            onSelectAllPupils = onSelectAllPupils,
            modifier = Modifier
                .padding(contentPadding)
                .background(LocalColors.BackgroundDefaultDark)
                .fillMaxSize()
        )
    }
}