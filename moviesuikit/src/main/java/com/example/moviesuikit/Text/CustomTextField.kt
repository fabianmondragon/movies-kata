package com.example.moviesuikit.Text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class CustomTextField {
    companion object{

        @Composable
        fun NormalTextField (
            value: String,
            onValueChange: (String) -> Unit,
            placeholder: String,

        ){
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(placeholder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
        }

        @Composable
        fun TextFieldWithError(
            value: String,
            onValueChange: (String) -> Unit,
            isError: Boolean,
            placeholder: String,
        )
        {
            TextField(
                value = value,
                onValueChange = onValueChange,
                isError = isError,
                placeholder = { Text(placeholder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
        }

        @Composable
        fun CustomTextFieldPassword(
            value: String,
            onValueChange: (String) -> Unit,
            placeholder: String,
            isError: Boolean = false
            ){
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    isError = isError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = { Text(placeholder) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
                )
            }
        }
}