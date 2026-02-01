package com.tutorlog.app.feature.students.add_pupil_in_group.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.domain.model.local.UIAdditionPupil
import com.tutorlog.app.utils.getInitials


@Composable
fun AddPupilToGroupComposable(
    groupName: String,
    studentList: List<UIAdditionPupil>,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit,
    onSelectAllClick: () -> Unit,
    onStudentToggled: (Int) -> Unit,
    isButtonLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = LocalColors.BackgroundDefaultDark,
        topBar = {
            TopHeader(
                onBackClick = {
                    onBackClick.invoke()
                },
                onSelectAllClick = {
                    onSelectAllClick.invoke()
                },
                groupName = groupName
            )
        },
        bottomBar = {
            BottomActionButton(
                count = studentList.count { it.isSelected == true },
                onClick = {
                    onAddClick.invoke()
                },
                isButtonLoading = isButtonLoading
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Text(
                    text = "Add Pupils",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
                )
            }

            item {
                SearchBar()
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                Text(
                    text = "ALL STUDENTS",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(LocalColors.Gray800)
                ) {
                    studentList.forEachIndexed { index, item ->
                        StudentItem(
                            student = item,
                            onClick = {
                                onStudentToggled.invoke(item.id)
                            },
                            showDivider = index != studentList.lastIndex
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TopHeader(
    groupName: String,
    onBackClick: () -> Unit, onSelectAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalColors.BackgroundDefaultDark,)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = groupName,
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Select All",
            color = LocalColors.PrimaryGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clickable(onClick = onSelectAllClick)
                .padding(8.dp)
        )
    }
}

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 16.sp),
        cursorBrush = SolidColor(LocalColors.PrimaryGreen),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LocalColors.Gray800, RoundedCornerShape(16.dp))
                    .padding(horizontal = 24.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(contentAlignment = Alignment.CenterStart) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Search by name...",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun StudentItem(
    student: UIAdditionPupil,
    onClick: () -> Unit,
    showDivider: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LocalColors.Red300),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.name.getInitials(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Text Info
            Column {
                Text(
                    text = student.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                if (student.details.isNotEmpty()) {
                    Text(
                        text = student.details,
                        color = LocalColors.Gray400,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        // Custom Checkbox
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (student.isSelected) LocalColors.PrimaryGreen else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (student.isSelected) LocalColors.PrimaryGreen else Color(0xFF4B5563),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (student.isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = LocalColors.Gray900,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }

    if (showDivider) {
        HorizontalDivider(
            color = LocalColors.DividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 0.dp) // Edge to edge divider inside card
        )
    }
}

@Composable
fun BottomActionButton(
    count: Int,
    onClick: () -> Unit,
    isButtonLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LocalColors.Gray900.copy(alpha = 0.95f))
            .padding(16.dp)
            .padding(bottom = 16.dp)
    ) {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalColors.PrimaryGreen,
                contentColor = LocalColors.Gray900
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            if (isButtonLoading) {
                CircularProgressIndicator(
                    color = LocalColors.Black,
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 4.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add $count Pupils to Group",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddPupilToGroupComposable() {
    AddPupilToGroupComposable(
        studentList = listOf(
            UIAdditionPupil(
                id = 1,
                name = "John Doe",
                details = "Grade 10, Class A",
                isSelected = false
            ),
            UIAdditionPupil(
                id = 2,
                name = "Jane Smith",
                details = "Grade 1",
                isSelected = false
            ),
            UIAdditionPupil(
                id = 3,
                name = "Alice Johnson",
                details = "Grade",
                isSelected = true
            )
        ),
        onAddClick = {

        },
        onBackClick = {},
        onSelectAllClick = {},
        groupName = "Tabahi",
        onStudentToggled = {},
        isButtonLoading = true
    )
}