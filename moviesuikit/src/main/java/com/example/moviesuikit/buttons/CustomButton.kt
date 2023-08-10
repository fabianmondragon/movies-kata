package com.example.moviesuikit.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesuikit.theme.Brown

class CustomButton {
    companion object {

        @Composable
        fun CustomButtonFull(
            text: String,
            onclick: () -> Unit
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Brown),
                contentPadding = PaddingValues(16.dp),
                onClick = onclick

            ) {
                Text(text = text)
            }
        }

        @Composable
        fun NormalButton (
            text: String,
            onclick: () -> Unit
        ) {
            Button(
                onClick = onclick,
                colors = ButtonDefaults.buttonColors(containerColor = Brown)
            ) {
                Text(text = text)
            }
        }
    }
}


