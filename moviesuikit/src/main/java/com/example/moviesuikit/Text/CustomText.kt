package com.example.moviesuikit.Text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

class CustomText {
    companion object{
        @Composable
        fun CustomTextWithClickablePart(
            onClick: () -> Unit,
            firstPartWithoutBold: String,
            secondPartWithBold: String
        ) {
            val annotatedString = buildAnnotatedString() {
                append(firstPartWithoutBold)
                pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                append(secondPartWithBold)
                pop()
            }

            ClickableText(text = annotatedString, onClick = {
                onClick.invoke()
            })
        }
    }

}