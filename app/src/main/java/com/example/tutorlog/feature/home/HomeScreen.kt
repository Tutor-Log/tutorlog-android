package com.example.tutorlog.feature.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.BottomNavigationBar
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.TFullScreenErrorComposable
import com.example.tutorlog.design.TFullScreenLoaderComposable
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.feature.event_detail.EventDetailNavArgs
import com.example.tutorlog.feature.home.composables.DateSliderComposable
import com.example.tutorlog.feature.home.composables.EventCardComposable
import com.example.tutorlog.feature.home.composables.ExpandableFabMenu
import com.example.tutorlog.feature.home.composables.TopInfoBarComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddEventScreenDestination
import com.ramcosta.composedestinations.generated.destinations.AddGroupScreenDestination
import com.ramcosta.composedestinations.generated.destinations.AddPupilScreenDestination
import com.ramcosta.composedestinations.generated.destinations.EventDetailScreenDestination
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
    val context = LocalContext.current
    viewModel.collectSideEffect {
        when (it) {
            is HomeScreenSideEffect.NavigateToStudentsScreen -> {
                navigator.navigate(StudentScreenDestination)
            }

            is HomeScreenSideEffect.NavigateToAddEventScreen -> {
                navigator.navigate(AddEventScreenDestination)
            }

            is HomeScreenSideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }

            is HomeScreenSideEffect.NavigateToEventDetail -> {
                navigator.navigate(
                    EventDetailScreenDestination(
                        navArgs = EventDetailNavArgs(
                            eventId = it.eventId,
                            title = it.title,
                            description = it.description,
                            date = it.date,
                            time = it.time
                        )
                    )
                )
            }

            is HomeScreenSideEffect.NavigateAddGroupScreen -> {
                navigator.navigate(AddGroupScreenDestination)
            }

            is HomeScreenSideEffect.NavigateAddPupilScreen -> {
                navigator.navigate(AddPupilScreenDestination)
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
                viewModel.getHomeScreenContent(
                    startDate = state.currentDate,
                    endDate = state.currentDate
                )
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
            ExpandableFabMenu(
                onAddEvent = {
                    viewModel.navigateToAddEvent()
                },
                onAddGroup = {
                    viewModel.navigateToAddGroup()
                },
                onAddPupil = {
                    viewModel.navigateToAddPupil()
                }
            )
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
                    viewModel.onDateChanged(date = it)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                if (state.isEventLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            strokeWidth = 4.dp
                        )
                    }
                } else {
                    if (state.classList.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .padding(top = 200.dp).fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No class scheduled yet",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Add a class to start",
                                color = Color.Gray,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    viewModel.navigateToAddEvent()
                                },
                                modifier = Modifier
                                    .height(48.dp)
                                    .fillMaxWidth(0.5f),
                                colors = ButtonDefaults.buttonColors(containerColor = LocalColors.PrimaryGreen),
                                shape = CircleShape
                            ) {
                                Text(
                                    "Schedule Event",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                )
                            }
                        }

                    } else {
                        state.classList.forEach { item ->
                            EventCardComposable(
                                isRepeat = item.isRepeat,
                                time = item.time,
                                meridiem = item.meridiem,
                                title = item.title,
                                subtitle = item.subtitle,
                                description = item.description,
                                onClick = {
                                    viewModel.navigateToEventDetail(
                                        item.id,
                                        title = item.title,
                                        description = item.description,
                                        date = state.currentDate,
                                        time = "${item.time} ${item.meridiem}"
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}