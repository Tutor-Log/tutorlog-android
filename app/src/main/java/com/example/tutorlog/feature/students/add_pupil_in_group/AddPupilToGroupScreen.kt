package com.example.tutorlog.feature.students.add_pupil_in_group

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.feature.students.add_pupil_in_group.composable.AddPupilToGroupComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import org.orbitmvi.orbit.compose.collectSideEffect


@Destination<RootGraph>(
    navArgs = AddPupilToGroupNavArgs::class
)
@Composable
fun AddPupilToGroupScreen(
    navArgs: AddPupilToGroupNavArgs,
    modifier: Modifier = Modifier,
    viewModel: AddPupilToGroupViewModel = hiltViewModel(),
) {


    viewModel.collectSideEffect { sideEffect ->
    }

//    Scaffold { contentPading ->
//        AddPupilToGroupComposable(
//            modifier = Modifier.padding(contentPading),
//        )
//
//    }

}