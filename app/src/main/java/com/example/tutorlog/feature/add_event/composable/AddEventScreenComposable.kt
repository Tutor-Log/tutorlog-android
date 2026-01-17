package com.example.tutorlog.feature.add_event.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlog.R
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.domain.types.EventFrequencyType
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreenComposable(
    eventName: String,
    onEventNameEntered: (String) -> Unit,
    description: String,
    onDescriptionEntered: (String) -> Unit,
    date: String,
    startTime: String,
    endTime: String,
    repeatUntil: String,
    frequency: EventFrequencyType,
    onDateClicked: (Pair<Int, Long>) -> Unit,
    onTimeClicked: (Pair<Int, String>) -> Unit,
    onFrequencyClicked: (EventFrequencyType) -> Unit,
    selectedDayList: List<Int>,
    onSelectedDayClick: (Int) -> Unit,
    selectablePupilList: List<UIAdditionPupil>,
    onBackClick: () -> Unit = {},
    onSubmit: () -> Unit = {},
    onPupilToggled: (Int) -> Unit = {},
    onSelectAllPupils: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showDatePicker by remember { mutableStateOf(DatePickerMode.NONE) }
    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= System.currentTimeMillis()
            }
        }
    )

    var activeTimePickerMode by remember { mutableStateOf(TimePickerMode.NONE) }
    val currentTime = LocalTime.now()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.hour,
        initialMinute = currentTime.minute,
        is24Hour = true
    )
    var showPupilBottomSheet by remember { mutableStateOf(false) }

    // Repeat Logic

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                tint = LocalColors.Gray400,
                contentDescription = "",
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    onClick = {
                        onBackClick.invoke()
                    }
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Create Class Event",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
        // Event Name
        AddEventTextField(
            label = "Title",
            value = eventName,
            onValueChange = { onEventNameEntered.invoke(it) },
            placeholder = "e.g. Masterclass with Jane"
        )

        // Description
        AddEventTextField(
            label = "Description",
            value = description,
            onValueChange = { onDescriptionEntered.invoke(it) },
            placeholder = "Add more details about this session...",
            singleLine = false,
            height = 80.dp
        )

        // Participants
        Column {
            Text(
                "Participants",
                color = LocalColors.Gray400,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            ParticipantButton(
                icon = R.drawable.ic_person,
                label = if (selectablePupilList.any { it.isSelected == true })
                    "${selectablePupilList.count { it.isSelected }} pupils selected" else "Add Pupil",
                onClick = { showPupilBottomSheet = true },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Date
        DateSelectorRowComposable(
            onClick = { showDatePicker = DatePickerMode.START },
            label = "Date",
            date = date,
        )

        // Time Row
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                TappableIconComposable(
                    label = "Start Time",
                    value = startTime,
                    onClick = {
                        activeTimePickerMode = TimePickerMode.START
                    },
                    icon = R.drawable.ic_start_time
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                TappableIconComposable(
                    label = "End Time",
                    value = endTime,
                    onClick = {
                        activeTimePickerMode = TimePickerMode.END
                    },
                    icon = R.drawable.ic_end_time
                )
            }
        }

        // Frequency
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                "Frequency",
                color = LocalColors.Gray400,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FrequencyOptionComposable(
                    text = "One-time",
                    selected = frequency == EventFrequencyType.ONE_TIME,
                    onClick = { onFrequencyClicked.invoke(frequency) },
                    modifier = Modifier.weight(1f)
                )
                FrequencyOptionComposable(
                    text = "Repeat",
                    selected = frequency == EventFrequencyType.REPEAT,
                    onClick = {
                        onFrequencyClicked.invoke(frequency)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            if (frequency == EventFrequencyType.REPEAT) {
                Column(
                    modifier = Modifier.padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Occurs on Header
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Occurs on",
                            color = LocalColors.Gray400,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        // Select Weekdays Chip
                        val allWeekdaysSelected = (1..5).all { selectedDayList.contains(it) }
                        Surface(
                            shape = CircleShape,
                            color = if (allWeekdaysSelected) LocalColors.PrimaryGreen else LocalColors.Gray800,
                            border = BorderStroke(
                                1.dp,
                                if (allWeekdaysSelected) LocalColors.PrimaryGreen else LocalColors.Gray700
                            ),
                            modifier = Modifier.clickable {

                            }
                        ) {
                            Text(
                                "Select Weekdays",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = if (allWeekdaysSelected) Color.Black else Color.LightGray
                            )
                        }
                    }

                    // Day Circles
                    DayCircle(
                        selectedList = selectedDayList,
                        onClick = {
                            onSelectedDayClick.invoke(it)
                        }
                    )

                    DateSelectorRowComposable(
                        onClick = { showDatePicker = DatePickerMode.REPEAT_UNTIL },
                        label = "Repeat Until",
                        date = repeatUntil,
                    )
                }
            }
        }

        // Submit Button
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LocalColors.PrimaryGreen),
            shape = CircleShape
        ) {
            Text(
                "Schedule Event",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Settings, contentDescription = null, tint = Color.Black)
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
    if (showDatePicker != DatePickerMode.NONE) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = DatePickerMode.NONE },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (showDatePicker == DatePickerMode.START) {
                            onDateClicked.invoke(Pair(0, datePickerState.selectedDateMillis ?: 0L))
                        } else {
                            onDateClicked.invoke(Pair(1, datePickerState.selectedDateMillis ?: 0L))
                        }
                        showDatePicker = DatePickerMode.NONE
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = DatePickerMode.NONE }) {
                    Text("Cancel")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (activeTimePickerMode != TimePickerMode.NONE) {
        DialTimePickerDialog(
            onDismiss = { activeTimePickerMode = TimePickerMode.NONE },
            onConfirm = {
                onTimeClicked.invoke(
                    if (activeTimePickerMode == TimePickerMode.START) {
                        Pair(
                            0, "${timePickerState.hour}-${timePickerState.minute}"
                        )
                    } else {
                        Pair(
                            1, "${timePickerState.hour}-${timePickerState.minute}"
                        )
                    }
                )
                activeTimePickerMode = TimePickerMode.NONE
            }
        ) {
            // This is the actual clock UI
            TimePicker(state = timePickerState)
        }
    }

    // Pupil Selection Bottom Sheet
    if (showPupilBottomSheet) {
        PupilSelectionBottomSheet(
            pupils = selectablePupilList,
            onDismiss = { showPupilBottomSheet = false },
            onPupilToggled = onPupilToggled,
            onSelectAll = onSelectAllPupils
        )
    }
}

// --- Helper Components ---
@Composable
fun DialTimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("OK")
            }
        },
        text = {
            content()
        }
    )
}

@Composable
fun ParticipantButton(
    @DrawableRes icon: Int,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        color = LocalColors.Gray800,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, LocalColors.Gray700),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(Color(0xFF374151).copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = LocalColors.PrimaryGreen,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(label, color = Color(0xFFE5E7EB), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BottomSheetSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 16.sp),
        cursorBrush = SolidColor(LocalColors.PrimaryGreen),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(LocalColors.Gray900, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

enum class TimePickerMode {
    START, END, NONE
}

enum class DatePickerMode {
    START, REPEAT_UNTIL, NONE
}

@Preview
@Composable
private fun PreviewAddEventScreen() {
    AddEventScreenComposable(
        selectablePupilList = emptyList(),
        eventName = "Cool class",
        onEventNameEntered = {},
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColors.BackgroundDefaultDark),
        description = "",
        onDescriptionEntered = {},
        startTime = "",
        endTime = "",
        date = "",
        frequency = EventFrequencyType.REPEAT,
        onDateClicked = {},
        onTimeClicked = {},
        onFrequencyClicked = {},
        selectedDayList = listOf(1, 4),
        onSelectedDayClick = {},
        repeatUntil = ""
    )
}