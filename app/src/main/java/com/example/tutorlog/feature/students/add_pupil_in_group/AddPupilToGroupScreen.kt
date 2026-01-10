package com.example.tutorlog.feature.students.add_pupil_in_group

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.TFullScreenErrorComposable
import com.example.tutorlog.design.TFullScreenLoaderComposable
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.feature.students.add_pupil_in_group.composable.AddPupilToGroupComposable
import com.example.tutorlog.feature.students.group_detail.GroupDetailNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddPupilToGroupScreenDestination
import com.ramcosta.composedestinations.generated.destinations.GroupDetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect


@Destination<RootGraph>(
    navArgs = AddPupilToGroupNavArgs::class
)
@Composable
fun AddPupilToGroupScreen(
    navArgs: AddPupilToGroupNavArgs,
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: AddPupilToGroupViewModel = hiltViewModel(),
) {

    val state by viewModel.collectAsState()
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is AddPupilToGroupSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            is AddPupilToGroupSideEffect.NavigateToGroupDetailScreen -> {
                navigator.navigate(
                    GroupDetailScreenDestination(
                        navArgs = GroupDetailNavArgs(
                            groupId = sideEffect.groupId
                        )
                    )
                ) {
                    popUpTo(AddPupilToGroupScreenDestination) {
                        inclusive = true
                    }
                }
            }
        }
    }

    when (state.uiState) {
        UIState.SUCCESS -> {
            viewModel.filterOnlyNonAddedMembers(
                allPupilList = state.allPupilList,
                addedPupilList = state.addedPupilList
            )
            InitializeAddPupilToGroupScreen(
                state = state,
                viewModel = viewModel,
                modifier = modifier,
                navigator = navigator
            )
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getAllAddPupilToGroupData()
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
private fun InitializeAddPupilToGroupScreen(
    state: AddPupilToGroupState,
    navigator: DestinationsNavigator,
    viewModel: AddPupilToGroupViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
    ) { contentPadding ->
        AddPupilToGroupComposable(
            modifier = Modifier.padding(contentPadding),
            studentList = state.pupilList,
            onAddClick = {
                viewModel.addSelectedPupilToGroup(
                    list = state.pupilList
                )
            },
            onBackClick = {
                navigator.popBackStack()
            },
            onSelectAllClick = {

            },
            groupName = state.groupName,
            isButtonLoading = state.isButtonLoading,
            onStudentToggled = {
                viewModel.pupilToggled(
                    pupilId = it,
                    pupilList = state.pupilList
                )
            }
        )
    }
}