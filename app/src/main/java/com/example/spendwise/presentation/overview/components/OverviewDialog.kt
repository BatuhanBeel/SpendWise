package com.example.spendwise.presentation.overview.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.presentation.add_edit.components.TextFieldRow
import com.example.spendwise.presentation.overview.OverviewDialogViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun OverviewDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogViewModel: OverviewDialogViewModel = hiltViewModel()
) {
    val spendingTextFieldState = dialogViewModel.balanceTextFieldState
    val scope = rememberCoroutineScope()

    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            TextFieldRow(
                title = "",
                value = spendingTextFieldState.text,
                error = spendingTextFieldState.error,
                errorText = spendingTextFieldState.errorText,
                onTextChanged = { dialogViewModel.onBalanceChanged(it)  },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    scope.launch(Dispatchers.IO){
                        if (dialogViewModel.isInputValid()){
                            onConfirmation()
                        }
                    }

                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}