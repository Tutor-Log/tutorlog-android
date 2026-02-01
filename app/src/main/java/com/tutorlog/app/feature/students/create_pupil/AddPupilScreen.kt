package com.tutorlog.app.feature.students.create_pupil


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.feature.students.create_pupil.composable.CustomTextFieldComposable
import com.tutorlog.app.feature.students.create_pupil.composable.GroupDropdownComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddPupilScreenDestination
import com.ramcosta.composedestinations.generated.destinations.StudentScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination<RootGraph>
@Composable
fun AddPupilScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AddPupilViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current
    viewModel.collectSideEffect {
        when(it) {
            is AddPupilSideEffect.NavigateToStudentScreen -> {
                navigator.navigate(StudentScreenDestination) {
                    popUpTo(AddPupilScreenDestination) {
                        inclusive = true
                    }
                }
            }
            is AddPupilSideEffect.ShowToast -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        modifier = modifier
            .background(color = LocalColors.BackgroundDefaultDark)
            .windowInsetsPadding(WindowInsets.statusBars),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = LocalColors.Gray400
                    )
                }
                Text(
                    text = "Add New Pupil",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                // Empty box to balance the row
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(LocalColors.Gray900)
                    .padding(bottom = 8.dp)
            ) {
                Button(
                    onClick = {
                        if (state.isButtonLoading.not()) {
                            viewModel.addPupil(
                                name = state.name,
                                email = state.email,
                                phone = state.phone,
                                group = null
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColors.PrimaryGreen,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    if (state.isButtonLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.Black,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Save Pupil",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LocalColors.BackgroundDefaultDark)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextFieldComposable(
                value = state.name,
                onValueChange = {
                    viewModel.onNameChange(it)
                },
                label = "Pupil Name",
                placeholder = "e.g. Jane Doe",
                icon = Icons.Default.Person,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextFieldComposable(
                value = state.phone,
                onValueChange = {
                    if (it.length <= 10 && it.isDigitsOnly()) {
                        viewModel.onPhoneChange(it)
                    }
                },
                label = "Phone Number",
                placeholder = "+1 (555) 000-0000",
                icon = Icons.Default.Call,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                prefix = {
                    Text(
                        text = "+91",
                        color = Color.White
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextFieldComposable(
                value = state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                label = "Email Address",
                placeholder = "jane@example.com",
                icon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )

//            Spacer(modifier = Modifier.height(16.dp))
//
//            GroupDropdownComposable(
//                selectedGroup = state.selectedGroup,
//                onGroupSelected = {
//                    viewModel.onDropDownClick(it)
//                },
//                optionList = state.groupOptionList
//            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}