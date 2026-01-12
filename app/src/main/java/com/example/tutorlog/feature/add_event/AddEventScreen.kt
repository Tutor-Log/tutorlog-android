package com.example.tutorlog.feature.add_event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.feature.add_event.composable.AddEventScreenComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>
@Composable
fun AddEventScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = hiltViewModel()
) {

    viewModel.collectSideEffect {
        when (it) {

        }
    }
    InitializeAddEventScreen()

}

@Composable
private fun InitializeAddEventScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) { contentPadding ->
        AddEventScreenComposable(
            modifier = Modifier
                .padding(contentPadding)
                .background(LocalColors.BackgroundDefaultDark)
                .fillMaxSize()
        )
    }
}