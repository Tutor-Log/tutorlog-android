package com.tutorlog.app.domain.model.local

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class UISettingsItem(
    val icon: ImageVector,
    val title: String,
    val iconBackgroundColor: Color,
    val iconTintColor: Color,
    val trailingText: String? = null,
    val onClick: () -> Unit
)
