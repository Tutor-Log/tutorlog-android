package com.tutorlog.app.feature.event_detail

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.design.TFullScreenErrorComposable
import com.tutorlog.app.design.TFullScreenLoaderComposable
import com.tutorlog.app.domain.types.UIState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>(
    navArgs = EventDetailNavArgs::class
)
@Composable
fun EventDetailScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    args: EventDetailNavArgs,
    viewModel: EventDetailViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect {
        when(it) {
            is EventDetailSideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    when (state.uiState) {

        UIState.SUCCESS -> {
            InitializeEventDetailScreen(
                state = state,
                viewModel = viewModel,
                navigator = navigator,
                args = args
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
    args: EventDetailNavArgs,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
    ) { contentPadding ->

        EventDetailsScreenComposable(
            modifier = Modifier.padding(contentPadding),
            onDeleteEventClick = {
                viewModel.onDeleteEvent(
                    eventId = args.eventId
                )
            },
            onClickBack = {
                navigator.popBackStack()
            },
            title = state.title,
            description = state.description,
            pupilList = state.pupilList,
            date = state.date,
            time = state.time,
            isButtonLoading = state.isButtonLoading
        )
    }

}