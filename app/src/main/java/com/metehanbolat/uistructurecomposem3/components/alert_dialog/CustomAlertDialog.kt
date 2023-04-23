package com.metehanbolat.uistructurecomposem3.components.alert_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.metehanbolat.uistructurecomposem3.R

@Composable
fun DisplayAlertDialog(
    title: String,
    text: String,
    isDialogOpened: Boolean,
    onDismissDialog: () -> Unit,
    onConfirmClicked: () -> Unit
) {
    if (isDialogOpened) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = text,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmClicked()
                        onDismissDialog()
                    }
                ) {
                    Text(text = stringResource(id = R.string.dialog_yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = onDismissDialog) {
                    Text(text = stringResource(id = R.string.dialog_no))
                }
            },
            onDismissRequest = onDismissDialog
        )
    }
}

@Preview(showBackground = true)

@Composable
fun DisplayAlertDialogPreview() {

    var isDialogOpened by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { isDialogOpened = true }
        ) {
            Text(text = stringResource(id = R.string.button_text))
        }
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.dialog_title),
        text = stringResource(id = R.string.dialog_text),
        isDialogOpened = isDialogOpened,
        onDismissDialog = { isDialogOpened = false },
        onConfirmClicked = { isDialogOpened = false }
    )
}