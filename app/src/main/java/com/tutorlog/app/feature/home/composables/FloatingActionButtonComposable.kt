package com.tutorlog.app.feature.home.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutorlog.app.R
import com.tutorlog.app.design.LocalColors

@Composable
fun ExpandableFabMenu(
    onAddPupil: () -> Unit,
    onAddEvent: () -> Unit,
    onAddGroup: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Option 1
                SmallFloatingActionButton(
                    onClick = { onAddEvent.invoke() },
                    containerColor = LocalColors.PrimaryGreen,
                    modifier = Modifier.width(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Event",
                            color = LocalColors.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = "",
                            modifier = Modifier.size(16.dp),
                            tint = LocalColors.Black
                        )
                    }
                }

                // Option 2
                SmallFloatingActionButton(
                    onClick = { onAddPupil.invoke() },
                    containerColor = LocalColors.PrimaryGreen,
                    modifier = Modifier.width(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pupil",
                            color = LocalColors.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_person),
                            contentDescription = "",
                            modifier = Modifier.size(16.dp),
                            tint = LocalColors.Black
                        )
                    }
                }
                SmallFloatingActionButton(
                    onClick = { onAddGroup.invoke() },
                    containerColor = LocalColors.PrimaryGreen,
                    modifier = Modifier.width(80.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Group",
                            color = LocalColors.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_group),
                            contentDescription = "",
                            modifier = Modifier.size(16.dp),
                            tint = LocalColors.Black
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { expanded = !expanded },
            containerColor = LocalColors.PrimaryGreen,
            shape = FloatingActionButtonDefaults.shape
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.Close else Icons.Filled.Add,
                contentDescription = "Expand menu",
                tint = LocalColors.Black
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFAB() {
    ExpandableFabMenu(
        onAddGroup = {},
        onAddPupil = {},
        onAddEvent = {}
    )
}