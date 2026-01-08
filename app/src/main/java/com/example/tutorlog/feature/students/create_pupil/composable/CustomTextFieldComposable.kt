package com.example.tutorlog.feature.students.create_pupil.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlog.design.LocalColors


@Composable
fun CustomTextFieldComposable(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            ),
            onValueChange = { value ->
                onValueChange(value.text)
            },
            placeholder = { Text(placeholder, color = Color.Gray) },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            trailingIcon = trailingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = onClick != null) { onClick?.invoke() },
            enabled = enabled,
            readOnly = readOnly,
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = LocalColors.Gray800,
                unfocusedContainerColor = LocalColors.Gray800,
                disabledContainerColor = LocalColors.Gray800,
                focusedBorderColor = LocalColors.PrimaryGreen,
                unfocusedBorderColor = LocalColors.Gray700,
                cursorColor = LocalColors.PrimaryGreen,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            keyboardOptions = keyboardOptions,
            prefix = prefix,
        )
    }
}