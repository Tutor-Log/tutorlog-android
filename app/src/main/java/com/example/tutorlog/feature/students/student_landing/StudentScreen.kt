package com.example.tutorlog.feature.students.student_landing

import androidx.compose.animation.AnimatedContent
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.tutorlog.feature.students.composables.GroupInfoCardComposable
import com.example.tutorlog.feature.students.composables.PupilGroupSliderComposable
import com.example.tutorlog.feature.students.composables.PupilInfoCardComposable
import com.example.tutorlog.feature.students.group_detail.GroupDetailNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddGroupScreenDestination
import com.ramcosta.composedestinations.generated.destinations.AddPupilScreenDestination
import com.ramcosta.composedestinations.generated.destinations.GroupDetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>()
@Composable
fun StudentScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: StudentViewModel = hiltViewModel()
) {


    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is StudentScreenSideEffect.NavigateToHomeScreen -> {
                navigator.popBackStack()
            }

            is StudentScreenSideEffect.NavigateToAddPupil -> {
                navigator.navigate(AddPupilScreenDestination)
            }

            is StudentScreenSideEffect.NavigateToAddGroup -> {
                navigator.navigate(AddGroupScreenDestination)
            }

            is StudentScreenSideEffect.NavigateToGroupDetail -> {
                navigator.navigate(
                    GroupDetailScreenDestination(
                        navArgs = GroupDetailNavArgs(
                            groupId = it.groupId
                        )
                    )
                )
            }
        }
    }
    when (state.screenState) {
        UIState.SUCCESS -> {
            InitializeStudentScreen(
                state = state,
                viewModel = viewModel,
                modifier = modifier
            )
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getStudentData()
            }
        }

        UIState.LOADING -> {
            TFullScreenLoaderComposable()
        }

        UIState.NONE -> {
        }
    }


}


@Composable
fun InitializeStudentScreen(
    state: StudentScreenState,
    viewModel: StudentViewModel,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            PupilGroupSliderComposable(
                selectedIndex = state.selectedIndex,
                onClick = {
                    viewModel.changeIndex(it)
                },
                modifier = Modifier
                    .background(color = LocalColors.BackgroundDefaultDark)
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 16.dp)
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
                        if (state.selectedIndex == 0) {
                            viewModel.navigateToAddPupil()
                        } else {
                            viewModel.navigateToAddGroup()
                        }
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
                .padding(contentPadding)
                .background(color = LocalColors.BackgroundDefaultDark)
                .fillMaxSize()
        ) {
            AnimatedContent(
                targetState = state.selectedIndex,
                label = ""
            ) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    if (index == 0) {
                        state.groupList.forEach { item ->
                            GroupInfoCardComposable(
                                name = item.name,
                                description = item.description,
                                onClick = {
                                    viewModel.navigateToGroupDetail(item.groupId)
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    } else {
                        state.studentList.forEach { item ->
                            PupilInfoCardComposable(
                                name = item.fullName,
                                phoneNumber = item.mobile,
                                gender = item.gender
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}


