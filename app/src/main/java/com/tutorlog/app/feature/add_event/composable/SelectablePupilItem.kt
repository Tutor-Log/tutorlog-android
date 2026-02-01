package com.tutorlog.app.feature.add_event.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.domain.model.local.UIAdditionPupil
import com.tutorlog.app.utils.getInitials

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