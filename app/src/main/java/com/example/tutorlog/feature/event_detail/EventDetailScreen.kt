package com.example.tutorlog.feature.event_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
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
import com.example.tutorlog.domain.types.UIState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState

@Destination<RootGraph>(
    navArgs = EventDetailNavArgs::class
)
@Composable
fun EventDetailScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    when (state.uiState) {

        UIState.SUCCESS -> {
            InitializeEventDetailScreen(
                state = state,
                viewModel = viewModel
            )
        }

        UIState.LOADING -> {
            TFullScreenLoaderComposable()
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getEventDetail()
            }
        }

        else -> {}
    }

}

@Composable
private fun InitializeEventDetailScreen(
    state: EventDetailState,
    viewModel: EventDetailViewModel,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
    ) { contentPadding ->

        EventDetailsScreenComposable(
            modifier = Modifier.padding(contentPadding)
        )
    }

}