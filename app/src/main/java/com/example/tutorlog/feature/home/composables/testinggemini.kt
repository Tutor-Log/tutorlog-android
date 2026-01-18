package com.example.tutorlog.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Colors & Theme Constants ---
val ColorBackground = Color(0xFF111827) // gray-900
val ColorSurface = Color(0xFF1F2937)    // gray-800
val ColorSurfaceHover = Color(0xFF283245)
val ColorPrimary = Color(0xFF38E07B)    // The green accent
val ColorTextPrimary = Color.White
val ColorTextSecondary = Color(0xFF9CA3AF) // gray-400
val ColorBorder = Color(0xFF374151)     // gray-700
val ColorPurple = Color(0xFFA855F7)
val ColorBlueBg = Color(0xFF1E3A8A).copy(alpha = 0.3f)
val ColorBlueText = Color(0xFF93C5FD)

// --- Main Screen ---
@Composable
fun TeacherDashboardScreen() {
    Scaffold(
        containerColor = ColorBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle Add */ },
                containerColor = ColorPrimary,
                contentColor = Color.Black,
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Class", modifier = Modifier.size(32.dp))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HeaderSection()
        }
    }
}

// --- Header Section ---
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Menu */ }) {
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = ColorTextSecondary)
        }

        Text(
            text = "Schedule",
            color = ColorTextPrimary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )

        Box {
            IconButton(onClick = { /* Notifications */ }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = ColorTextSecondary)
            }
            // Notification Dot
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(ColorPrimary)
                    .border(2.dp, ColorBackground, CircleShape)
                    .align(Alignment.TopEnd)
                    .offset(x = (-8).dp, y = 8.dp)
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title.uppercase(),
        color = ColorTextSecondary,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
fun InfoChip(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ColorTextSecondary,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = ColorTextSecondary,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTeacherDashboard() {
    TeacherDashboardScreen()
}