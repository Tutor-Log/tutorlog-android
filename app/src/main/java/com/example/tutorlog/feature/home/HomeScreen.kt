package com.example.tutorlog.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.BottomNavigationBar
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.TFullScreenErrorComposable
import com.example.tutorlog.design.TFullScreenLoaderComposable
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.feature.home.composables.DateSliderComposable
import com.example.tutorlog.feature.home.composables.EventCardComposable
import com.example.tutorlog.feature.home.composables.TopInfoBarComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddEventScreenDestination
import com.ramcosta.composedestinations.generated.destinations.StudentScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            HomeScreenSideEffect.NavigateToStudentsScreen -> {
                navigator.navigate(StudentScreenDestination)
            }

            HomeScreenSideEffect.NavigateToAddEventScreen -> {
                navigator.navigate(AddEventScreenDestination)
            }
        }
    }
    when (state.uiState) {

        UIState.SUCCESS -> {
            InitializeHomeScreen(
                state = state,
                viewModel = viewModel
            )
        }

        UIState.LOADING -> {
            TFullScreenLoaderComposable()
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getHomeScreenContent()
            }
        }

        else -> {}
    }
}

@Composable
fun InitializeHomeScreen(
    state: HomeScreenState,
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            TopInfoBarComposable(
                imageUrl = state.image,
                name = state.userName.split(" ").first()
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = state.selectedBottomTab.index.toInt(),
                onTabSelected = {
                    viewModel.bottomTabSelected(it)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = LocalColors.LightGreen,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        viewModel.navigateToAddEvent()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = LocalColors.Black,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LocalColors.BackgroundDefaultDark)
                .padding(contentPadding)
                .padding(top = 16.dp)
        ) {
            DateSliderComposable(
                dayList = state.dateList,
                onClick = {

                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                state.classList.forEach { item ->
                    EventCardComposable(
                        isRepeat = item.isRepeat,
                        time = item.time,
                        meridiem = item.meridiem,
                        title = item.title,
                        subtitle = item.subtitle,
                        description = item.description
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}