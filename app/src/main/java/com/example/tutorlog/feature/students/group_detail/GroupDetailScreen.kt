package com.example.tutorlog.feature.students.group_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.design.TFullScreenErrorComposable
import com.example.tutorlog.design.TFullScreenLoaderComposable
import com.example.tutorlog.domain.model.local.UIGroupMemberInfo
import com.example.tutorlog.domain.types.UIState
import com.example.tutorlog.feature.students.create_group.AddGroupState
import com.example.tutorlog.feature.students.create_group.AddGroupViewModel
import com.example.tutorlog.feature.students.create_group.InitializeAddGroupScreen
import com.example.tutorlog.feature.students.group_detail.composable.GroupDetailComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState

@Destination<RootGraph>(
    navArgs = GroupDetailNavArgs::class
)
@Composable
fun GroupDetailScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: GroupDetailViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    when (state.screenState) {
        UIState.SUCCESS -> {
            InitializeGroupDetailScreen(
                state = state,
                viewModel = viewModel,
                modifier = modifier,
                navigator = navigator
            )
        }

        UIState.ERROR -> {
            TFullScreenErrorComposable {
                viewModel.getGroupDetail()
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
fun InitializeGroupDetailScreen(
    state: GroupDetailState,
    viewModel: GroupDetailViewModel,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
        containerColor = LocalColors.BackgroundDefaultDark,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            onClick = {
                                navigator.popBackStack()
                            }
                        )
                )
            }
        },
    ) { paddingValues ->
        GroupDetailComposable(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            groupName = "Something Crazy",
            memberList = listOf(
                UIGroupMemberInfo(
                    name = "John Doe",
                    memberId = 1,
                    gender = "M"
                ),
                UIGroupMemberInfo(
                    name = "Jane Smith",
                    memberId = 2,
                    gender = "F"
                ),
                UIGroupMemberInfo(
                    name = "Alice Johnson",
                    memberId = 3,
                    gender = "F"
                )
            ),
            onAddPupilClick = {

            }
        )
    }

}