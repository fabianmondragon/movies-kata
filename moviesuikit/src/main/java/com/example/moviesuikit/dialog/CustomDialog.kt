package com.example.moviesuikit.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.moviesuikit.buttons.CustomButton

class CustomDialog {

    companion object {

        @Composable
        fun CustomAlertDialog(
            message: String,
            title: String,
            okButton: String,
            cancelButton: String
        ) {
            if (message != "") {
                var showDialog by remember { mutableStateOf(true) }
                if (showDialog) {
                    AlertDialog(onDismissRequest = { showDialog = false }, title = {
                        Text(text = title)
                    }, text = {
                        Text(text = message)
                    }, confirmButton = {
                        CustomButton.NormalButton(
                            onclick = {
                                showDialog = false
                            }, text = okButton
                        )

                    }, dismissButton = {
                        CustomButton.NormalButton(
                            onclick = { showDialog = false },
                            text = cancelButton
                        )
                    })
                }
            }
        }
    }
}