package com.metehanbolat.uistructurecomposem3.components.clipboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClipboardScreen() {
    val clipboardManager = LocalClipboardManager.current

    var text by remember { mutableStateOf("") }

    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            trailingIcon = {
                IconButton(
                    onClick = {
                        clipboardManager.setText(
                            buildAnnotatedString {
                                append(text)
                            }
                        )
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClipboardScreenPreview() {
    ClipboardScreen()
}