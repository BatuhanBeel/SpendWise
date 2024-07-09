package com.example.spendwise.presentation.add_edit.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.spendwise.R
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(
    initialTime: Long = Calendar.getInstance().timeInMillis,
    onDismiss: () -> Unit,
    dateSelected: (Long) -> Unit
) {

    val datePickerState = rememberDatePickerState(
        yearRange = DatePickerDefaults.YearRange,
        initialSelectedDateMillis =  initialTime,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis<initialTime
            }
        }
    )
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    dateSelected(datePickerState.selectedDateMillis!!)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}