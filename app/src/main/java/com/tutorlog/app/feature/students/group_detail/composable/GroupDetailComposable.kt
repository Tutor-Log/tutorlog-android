package com.tutorlog.app.feature.students.group_detail.composable

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorlog.app.design.LocalColors
import com.tutorlog.app.domain.model.local.UIGroupMemberInfo
import com.tutorlog.app.utils.getInitials

@Composable
fun GroupDetailComposable(
    groupName: String,
    memberList: List<UIGroupMemberInfo>,
    onAddPupilClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        // Header Section
        Column(modifier = Modifier.padding(start = 4.dp, bottom = 24.dp)) {
            Text(
                text = groupName,
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp,
                lineHeight = 36.sp
            )
            Text(
                text = "${memberList.size} Pupils",
                color = LocalColors.Gray400,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Add Pupil Button
        Button(
            onClick = onAddPupilClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalColors.PrimaryGreen,
                contentColor = LocalColors.Gray900
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add New Pupil",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Group Members Section
        Text(
            text = "GROUP MEMBERS",
            color = LocalColors.Gray500,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
        )

        // Members List Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(LocalColors.Gray800)
        ) {

            memberList.forEachIndexed { index, member ->
                MemberItem(
                    name = member.name,
                    id = member.memberId,
                    gender = member.gender,
                    showDivider = index < memberList.lastIndex
                )
            }
        }

        // Extra spacing at bottom for scrolling
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun MemberItem(
    name: String,
    id: Int,
    gender: String,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Initials Avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (gender == "F") LocalColors.Red100 else LocalColors.Blue100),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = name.getInitials(),
                        color = LocalColors.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Name and Instrument
                Text(
                    text = name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                )
            }

            // More Options Icon
//            IconButton(
//                onClick = { /* Menu action */ },
//                modifier = Modifier.size(32.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Rounded.MoreVert,
//                    contentDescription = "Options",
//                    tint = LocalColors.Gray500,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
        }

        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 0.dp),
                thickness = 1.dp,
                color = LocalColors.Gray700.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewGroupDetail() {
    MaterialTheme {
        GroupDetailComposable(
            groupName = "Math Group",
            onAddPupilClick = {},
            memberList = listOf(
                UIGroupMemberInfo(
                    name = "John Doe",
                    memberId = 1,
                    gender = "M"
                ),
                UIGroupMemberInfo(
                    name = "Jane Smith",
                    memberId = 2,
                    gender = "F"
                ),
                UIGroupMemberInfo(
                    name = "Alice Johnson",
                    memberId = 3,
                    gender = "F"
                ),
                UIGroupMemberInfo(
                    name = "Bob Brown",
                    memberId = 4,
                    gender = "M"
                )
            )
        )
    }
}