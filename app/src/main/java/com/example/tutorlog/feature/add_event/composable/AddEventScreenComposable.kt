package com.example.tutorlog.feature.add_event.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlog.R
import com.example.tutorlog.design.LocalColors
import com.example.tutorlog.domain.model.local.UIAdditionGroup
import com.example.tutorlog.domain.model.local.UIAdditionPupil
import com.example.tutorlog.utils.getInitials


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreenComposable(
    selectablePupilList: List<UIAdditionPupil>,
    onBackClick: () -> Unit = {},
    onSubmit: () -> Unit = {},
    onPupilToggled: (Int) -> Unit = {},
    onSelectAllPupils: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showPupilBottomSheet by remember { mutableStateOf(false) }
    var showGroupBottomSheet by remember { mutableStateOf(false) }
    var frequency by remember { mutableStateOf("Repeat") } // "One-time" or "Repeat"

    // Form States
    var eventName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("14:00") }
    var endTime by remember { mutableStateOf("15:00") }

    // Repeat Logic
    val daysOfWeek = listOf("S", "M", "T", "W", "T", "F", "S")
    val selectedDays = remember { mutableStateListOf<Int>() }
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
        CustomTextField(
            label = "Event Name",
            value = eventName,
            onValueChange = { eventName = it },
            placeholder = "e.g. Masterclass with Jane"
        )

        // Description
        CustomTextField(
            label = "Description",
            value = description,
            onValueChange = { description = it },
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
                label = "Add Pupil",
                onClick = { showPupilBottomSheet = true },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Date
        CustomIconTextField(
            label = "Date",
            value = date,
            onValueChange = { date = it },
            icon = R.drawable.ic_calendar,
            isDate = true
        )

        // Time Row
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(modifier = Modifier.weight(1f)) {
                CustomTextField(
                    label = "Start Time",
                    value = startTime,
                    onValueChange = { startTime = it },
                    isTime = true
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                CustomTextField(
                    label = "End Time",
                    value = endTime,
                    onValueChange = { endTime = it },
                    isTime = true
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
                FrequencyOption(
                    text = "One-time",
                    selected = frequency == "One-time",
                    onClick = { frequency = "One-time" },
                    modifier = Modifier.weight(1f)
                )
                FrequencyOption(
                    text = "Repeat",
                    selected = frequency == "Repeat",
                    onClick = { frequency = "Repeat" },
                    modifier = Modifier.weight(1f)
                )
            }

            if (frequency == "Repeat") {
                Column(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Occurs on Header
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Occurs on",
                            color = LocalColors.Gray400,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        // Select Weekdays Chip
                        val allWeekdaysSelected = (1..5).all { selectedDays.contains(it) }
                        Surface(
                            shape = CircleShape,
                            color = if (allWeekdaysSelected) LocalColors.PrimaryGreen else LocalColors.Gray800,
                            border = BorderStroke(
                                1.dp,
                                if (allWeekdaysSelected) LocalColors.PrimaryGreen else LocalColors.Gray700
                            ),
                            modifier = Modifier.clickable {
                                if (allWeekdaysSelected) {
                                    selectedDays.removeAll(listOf(1, 2, 3, 4, 5))
                                } else {
                                    selectedDays.addAll(
                                        listOf(
                                            1,
                                            2,
                                            3,
                                            4,
                                            5
                                        ).filter { !selectedDays.contains(it) })
                                }
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        daysOfWeek.forEachIndexed { index, day ->
                            DayCircle(
                                text = day,
                                selected = selectedDays.contains(index),
                                onClick = {
                                    if (selectedDays.contains(index)) selectedDays.remove(index)
                                    else selectedDays.add(index)
                                }
                            )
                        }
                    }

                    // Repeat Until
                    CustomIconTextField(
                        label = "Repeat Until",
                        value = "",
                        onValueChange = {},
                        icon = R.drawable.ic_repeat,
                        isDate = true
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

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
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    singleLine: Boolean = true,
    height: androidx.compose.ui.unit.Dp? = null,
    isTime: Boolean = false
) {
    Column {
        Text(
            label,
            color = LocalColors.Gray400,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .then(if (height != null) Modifier.height(height) else Modifier)
                .border(1.dp, LocalColors.Gray700, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            placeholder = { Text(placeholder, color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LocalColors.Gray800,
                unfocusedContainerColor = LocalColors.Gray800,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LocalColors.PrimaryGreen
            ),
            singleLine = singleLine
        )
    }
}

@Composable
fun CustomIconTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    @DrawableRes icon: Int,
    isDate: Boolean = false
) {
    Column {
        Text(
            label,
            color = LocalColors.Gray400,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = LocalColors.Gray400,
                    modifier = Modifier.size(20.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, LocalColors.Gray700, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LocalColors.Gray800,
                unfocusedContainerColor = LocalColors.Gray800,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = LocalColors.PrimaryGreen
            ),
            singleLine = true
        )
    }
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
fun FrequencyOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (selected) LocalColors.PrimaryGreen else LocalColors.Gray800,
        border = if (selected) null else BorderStroke(1.dp, LocalColors.Gray700),
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
            Text(
                text,
                color = if (selected) Color.Black else Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DayCircle(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (selected) LocalColors.PrimaryGreen else LocalColors.Gray800)
            .border(
                1.dp,
                if (selected) LocalColors.PrimaryGreen else LocalColors.Gray700,
                CircleShape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = if (selected) Color.Black else Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PupilSelectionBottomSheet(
    pupils: List<UIAdditionPupil>,
    onDismiss: () -> Unit,
    onPupilToggled: (Int) -> Unit,
    onSelectAll: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredPupils = remember(searchQuery, pupils) {
        if (searchQuery.isEmpty()) pupils
        else pupils.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    
    val selectedCount = pupils.count { it.isSelected }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = LocalColors.Gray800,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Select Pupils",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Select All",
                        color = LocalColors.PrimaryGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .clickable(onClick = onSelectAll)
                            .padding(8.dp)
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = LocalColors.Gray400
                        )
                    }
                }
            }
            HorizontalDivider(color = LocalColors.Gray700)

            // Search Bar
            BottomSheetSearchBar(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search pupils..."
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Pupil List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(filteredPupils) { index, pupil ->
                    SelectablePupilItem(
                        pupil = pupil,
                        onClick = { onPupilToggled(pupil.id) },
                        showDivider = index != filteredPupils.lastIndex
                    )
                }
            }

            // Bottom Action
            if (selectedCount > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LocalColors.Gray900)
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LocalColors.PrimaryGreen,
                            contentColor = LocalColors.Gray900
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Done ($selectedCount selected)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
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

@Composable
fun SelectablePupilItem(
    pupil: UIAdditionPupil,
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LocalColors.Blue300),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = pupil.name.getInitials(),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Text Info
            Column {
                Text(
                    text = pupil.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                if (pupil.details.isNotEmpty()) {
                    Text(
                        text = pupil.details,
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
                .background(if (pupil.isSelected) LocalColors.PrimaryGreen else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (pupil.isSelected) LocalColors.PrimaryGreen else Color(0xFF4B5563),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (pupil.isSelected) {
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
            thickness = 1.dp
        )
    }
}

@Composable
fun SelectableGroupItem(
    group: UIAdditionGroup,
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
            // Group Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LocalColors.Purple300),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_group),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Text Info
            Column {
                Text(
                    text = group.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                if (group.description.isNotEmpty()) {
                    Text(
                        text = group.description,
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
                .background(if (group.isSelected) LocalColors.PrimaryGreen else Color.Transparent)
                .border(
                    width = 2.dp,
                    color = if (group.isSelected) LocalColors.PrimaryGreen else Color(0xFF4B5563),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (group.isSelected) {
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
            thickness = 1.dp
        )
    }
}


@Preview
@Composable
private fun PreviewAddEventScreen() {
    AddEventScreenComposable(
        selectablePupilList = emptyList(),
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColors.BackgroundDefaultDark)
    )
}