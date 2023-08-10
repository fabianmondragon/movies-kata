package com.example.moviesuikit.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CustomSpacer {
    companion object {

        @Composable
        fun lowSpacer() {
            Spacer(modifier = Modifier.height(4.dp))
        }

        @Composable
        fun mediumSpacer() {
            Spacer(modifier = Modifier.height(8.dp))
        }

        @Composable
        fun highSpacer() {
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}