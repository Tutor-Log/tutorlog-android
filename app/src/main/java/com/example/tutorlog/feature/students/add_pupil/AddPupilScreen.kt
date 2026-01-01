package com.example.tutorlog.feature.students.add_pupil


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.feature.students.add_pupil.composable.CustomTextFieldComposable
import com.example.tutorlog.feature.students.add_pupil.composable.GroupDropdownComposable
import com.example.tutorlog.feature.students.add_pupil.composable.ProfileImagePickerComposable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun AddPupilScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AddPupilViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var selectedGroup by remember { mutableStateOf("") }

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
            // Save Button (Fixed at bottom)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(LocalColors.Gray900)
                    .padding(bottom = 8.dp)
            ) {
                Button(
                    onClick = {

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
    ) { paddingValues ->
        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LocalColors.BackgroundDefaultDark)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Profile Image Picker
            ProfileImagePickerComposable()

            Spacer(modifier = Modifier.height(32.dp))

            // Form Fields
            CustomTextFieldComposable(
                value = name,
                onValueChange = { name = it },
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
                value = phone,
                onValueChange = { phone = it },
                label = "Phone Number",
                placeholder = "+1 (555) 000-0000",
                icon = Icons.Default.Call,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextFieldComposable(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "jane@example.com",
                icon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            GroupDropdownComposable(
                selectedGroup = selectedGroup,
                onGroupSelected = { selectedGroup = it }
            )

            // Extra space at bottom so content isn't hidden by button
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}