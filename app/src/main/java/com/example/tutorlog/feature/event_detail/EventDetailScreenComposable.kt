package com.example.tutorlog.feature.event_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Colors ---
val ColorDarkBg = Color(0xFF030712) // gray-950
val ColorSurface = Color(0xFF111827) // gray-900
val ColorCard = Color(0xFF1F2937)    // gray-800
val ColorPrimary = Color(0xFF38E07B) // The green accent
val ColorTextSecondary = Color(0xFF9CA3AF) // gray-400
val ColorBorder = Color(0xFF374151) // gray-700
val ColorBlueBadgeBg = Color(0xFF1E3A8A).copy(alpha = 0.4f)
val ColorBlueBadgeText = Color(0xFF60A5FA)
val ColorGreenBadgeBg = Color(0xFF14532D).copy(alpha = 0.4f)

@Composable
fun EventDetailsScreenComposable(
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
            item { HeroSection() }

            item {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    LessonFocusSection()
                    ParticipantsSection()
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(ColorSurface.copy(alpha = 0.8f), Color.Transparent)
                    )
                )
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavIconButton(Icons.AutoMirrored.Default.ArrowBack)
        }

        OutlinedButton(
            onClick = {},
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
            Icon(Icons.Default.Settings, null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cancel", fontWeight = FontWeight.Bold)
        }
    }
}

// --- Sections ---

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorCard)
            .padding(top = 72.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
    ) {
        Column {
            // Badges
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatusBadge("Private Class", ColorBlueBadgeText, ColorBlueBadgeBg)
                StatusBadge("Active", ColorPrimary, ColorGreenBadgeBg)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Jane Doe",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Date & Time
            HeroIconRow(Icons.Default.Settings, "Thursday, October 24, 2024")
            Spacer(modifier = Modifier.height(8.dp))
            HeroIconRow(Icons.Default.Settings, "09:00 AM - 09:45 AM (45 min)")
        }
    }
}

@Composable
fun LessonFocusSection() {
    Column {
        SectionTitle("Lesson Focus")
        CardContainer {
            Text(
                text = "Reviewing Vivaldi Concerto in A Minor, 1st Movement. Working specifically on the shifting in the third page and maintaining consistent tempo during the sixteenth note passages.",
                color = Color(0xFFD1D5DB), // gray-300
                lineHeight = 24.sp,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TagItem("Violin")
                TagItem("Grade 3")
                TagItem("Technique")
            }
        }
    }
}

@Composable
fun ParticipantsSection() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionTitle("Participants", paddingBottom = 0.dp)
            Text(
                text = "1 Person",
                color = ColorPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        CardContainer(padding = 12.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(ColorPrimary, Color(0xFF3B82F6))))
                        .border(2.dp, ColorCard, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("JD", fontWeight = FontWeight.Bold, color = ColorDarkBg)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Jane Doe", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Student • 3 years", color = ColorTextSecondary, fontSize = 12.sp)
                }

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = ColorTextSecondary
                )
            }
        }
    }
}

@Composable
fun NavIconButton(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ColorCard.copy(alpha = 0.8f))
            .border(1.dp, ColorBorder.copy(alpha = 0.5f), CircleShape)
            .blur(0.5.dp), // Subtle glass effect attempt
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
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
fun HeroIconRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = ColorPrimary, modifier = Modifier.size(20.dp))
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

@Composable
fun TagItem(text: String) {
    Surface(
        color = Color(0xFF374151), // gray-700
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF4B5563))
    ) {
        Text(
            text = text,
            color = Color(0xFFD1D5DB),
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EventDetailsPreview() {
    EventDetailsScreenComposable()
}