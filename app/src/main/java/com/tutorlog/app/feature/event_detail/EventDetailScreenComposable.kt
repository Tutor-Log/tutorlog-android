package com.tutorlog.app.feature.event_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.R
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.domain.model.local.UIPupilInfo

// --- Colors ---
val ColorDarkBg = Color(0xFF030712) // gray-950
val ColorSurface = Color(0xFF111827) // gray-900
val ColorCard = Color(0xFF1F2937)    // gray-800
val ColorPrimary = Color(0xFF38E07B) // The green accent
val ColorTextSecondary = Color(0xFF9CA3AF) // gray-400
val ColorBorder = Color(0xFF374151) // gray-700
val ColorGreenBadgeBg = Color(0xFF14532D).copy(alpha = 0.4f)

@Composable
fun EventDetailsScreenComposable(
    onClickBack: () -> Unit,
    onDeleteEventClick: () -> Unit,
    isButtonLoading: Boolean,
    title: String,
    description: String,
    date: String,
    time: String,
    pupilList: List<UIPupilInfo>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ColorSurface)
    ) {
        // Main Scrollable Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 200.dp) // Space for bottom bar
        ) {
            item {
                HeroSection(
                    title = title,
                    date = date,
                    time = time,
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    LessonFocusSection(
                        description = description
                    )
                    if (pupilList.isNotEmpty()) {
                        ParticipantsSection(
                            pupilList = pupilList
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .clickable(
                    onClick = {
                        onClickBack.invoke()
                    }
                )
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(ColorCard.copy(alpha = 0.8f))
                .border(1.dp, ColorBorder.copy(alpha = 0.5f), CircleShape)
                .blur(0.5.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
        }

        OutlinedButton(
            onClick = {
                onDeleteEventClick.invoke()
            },
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .height(56.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = ColorCard,
                contentColor = Color(0xFFF87171) // Red Text
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, ColorBorder),
        ) {
            if (isButtonLoading) {
                CircularProgressIndicator(
                    color = LocalColors.Red400
                )
            } else {
                Icon(Icons.Default.Settings, null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cancel", fontWeight = FontWeight.Bold)
            }
        }
    }
}

// --- Sections ---

@Composable
fun HeroSection(
    title: String,
    date: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(ColorCard)
            .padding(top = 72.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
    ) {
        Column {
            // Badges
            StatusBadge("Active", ColorPrimary, ColorGreenBadgeBg)

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = title,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Date & Time
            HeroIconRow(R.drawable.ic_calendar, date)
            Spacer(modifier = Modifier.height(8.dp))
            HeroIconRow(R.drawable.ic_start_time, time)
        }
    }
}

@Composable
fun LessonFocusSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle("Lesson Focus")
        CardContainer {
            Text(
                text = description,
                color = Color(0xFFD1D5DB), // gray-300
                lineHeight = 24.sp,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ParticipantsSection(
    pupilList: List<UIPupilInfo>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionTitle("Participants", paddingBottom = 0.dp)
            Text(
                text = "${pupilList.size} Pupil",
                color = ColorPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        pupilList.forEachIndexed { index, pupil ->
            CardContainer(padding = 12.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        ColorPrimary,
                                        Color(0xFF3B82F6)
                                    )
                                )
                            )
                            .border(2.dp, ColorCard, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = pupil.fullName.first().toString(),
                            fontWeight = FontWeight.Bold,
                            color = ColorDarkBg
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(pupil.fullName, color = Color.White, fontWeight = FontWeight.Bold)
                        Text(pupil.email, color = ColorTextSecondary, fontSize = 12.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun StatusBadge(text: String, textColor: Color, bgColor: Color) {
    Surface(
        color = bgColor,
        shape = RoundedCornerShape(100),
        border = androidx.compose.foundation.BorderStroke(1.dp, textColor.copy(alpha = 0.3f))
    ) {
        Text(
            text = text.uppercase(),
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun HeroIconRow(icon: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(icon), null, tint = ColorPrimary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, color = Color(0xFFD1D5DB), fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

@Composable
fun SectionTitle(text: String, paddingBottom: androidx.compose.ui.unit.Dp = 12.dp) {
    Text(
        text = text,
        color = Color(0xFF6B7280), // gray-500
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(bottom = paddingBottom)
    )
}

@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    padding: androidx.compose.ui.unit.Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(ColorCard.copy(alpha = 0.5f))
            .border(1.dp, ColorBorder, RoundedCornerShape(16.dp))
            .padding(padding),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun EventDetailsPreview() {
    EventDetailsScreenComposable(
        onDeleteEventClick = {},
        onClickBack = {},
        title = "Event Title",
        description = "Event Description",
        pupilList = emptyList(),
        date = "Date",
        time = "Time",
        isButtonLoading = true
    )
}