package com.example.tutorlog.feature.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tutorlog.design.LocalColors.Gray400
import com.example.tutorlog.design.LocalColors.Gray500
import com.example.tutorlog.design.LocalColors.Gray600
import com.example.tutorlog.design.LocalColors.Gray700
import com.example.tutorlog.design.LocalColors.Gray800
import com.example.tutorlog.design.LocalColors.Gray900
import com.example.tutorlog.domain.local.UISettingsItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onPersonalInfoClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAboutAppClick: () -> Unit,
    onLogoutClick: () -> Unit,
    userName: String,
    userRole: String,
    userPhotoUrl: String,
    appVersion: String
) {
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gray800,
                        contentColor = Color(0xFFF87171)
                    )
                ) {
                    Text(
                        text = "Log Out",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Version $appVersion",
                    fontSize = 12.sp,
                    color = Gray600,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        },
        containerColor = Gray900
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Profile",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = (-0.5).sp,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            ProfileCard(
                name = userName,
                role = userRole,
                image = userPhotoUrl,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Account Section
            SettingsSection(
                title = "ACCOUNT",
                items = listOf(
                    UISettingsItem(
                        icon = Icons.Default.Person,
                        title = "Personal Information",
                        iconBackgroundColor = Color(0xFF3B82F6).copy(alpha = 0.2f),
                        iconTintColor = Color(0xFF60A5FA),
                        onClick = onPersonalInfoClick
                    )
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Support Section
            SettingsSection(
                title = "SUPPORT",
                items = listOf(
                    UISettingsItem(
                        icon = Icons.Default.Info,
                        title = "Help Center",
                        iconBackgroundColor = Gray600.copy(alpha = 0.2f),
                        iconTintColor = Color(0xFFD1D5DB),
                        onClick = onHelpCenterClick
                    ),
                    UISettingsItem(
                        icon = Icons.Default.Info,
                        title = "About App",
                        iconBackgroundColor = Gray600.copy(alpha = 0.2f),
                        iconTintColor = Color(0xFFD1D5DB),
                        onClick = onAboutAppClick
                    )
                )
            )
        }
    }
}

@Composable
private fun ProfileCard(
    name: String,
    role: String,
    image: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray800
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            if (image.isNullOrEmpty().not()) {
                AsyncImage(
                    model = image,
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Gray700),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Gray400,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // User Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = role,
                    fontSize = 14.sp,
                    color = Gray400
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    items: List<UISettingsItem>
) {
    Column {
        // Section Title
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Gray500,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        // Settings Items
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Gray800
            )
        ) {
            items.forEachIndexed { index, item ->
                SettingsItemRow(item = item)

                if (index < items.size - 1) {
                    HorizontalDivider(
                        color = Gray700.copy(alpha = 0.5f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsItemRow(item: UISettingsItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = item.onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon with background
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(item.iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.iconTintColor,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title
        Text(
            text = item.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        // Trailing content
        if (item.trailingText != null) {
            Text(
                text = item.trailingText,
                fontSize = 14.sp,
                color = Gray500
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Gray500
        )
    }
}


@Preview
@Composable
fun PreviewProfileScreen(modifier: Modifier = Modifier) {
    SettingsScreen(
        onPersonalInfoClick = {},
        onHelpCenterClick = {},
        onAboutAppClick = {},
        onLogoutClick = {},
        userName = "John Doe",
        userRole = "Student",
        userPhotoUrl = "",
        appVersion = "8.2.22"
    )
}