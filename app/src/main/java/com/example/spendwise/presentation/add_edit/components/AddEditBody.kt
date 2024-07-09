package com.example.spendwise.presentation.add_edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.spendwise.R
import com.example.spendwise.domain.models.Category
import com.example.spendwise.presentation.add_edit.SpendingTextFieldState
import com.example.spendwise.presentation.util.getCategoryIcon

@Composable
fun AddEditBody(
    name: SpendingTextFieldState,
    amount: SpendingTextFieldState,
    due: Long,
    category: String,
    nameTextChanged: (String) -> Unit,
    amountTextChanged: (String) -> Unit,
    dueTextChanged: (Long) -> Unit,
    categoryTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var calenderVisibility by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .size(150.dp)
                .align(Alignment.CenterHorizontally),
            imageVector = getCategoryIcon(category = category),
            tint = MaterialTheme.colorScheme.tertiary,
            contentDescription = "Question mark icon"
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextFieldRow(
                title = stringResource(id = R.string.name),
                value = name.text,
                error = name.error,
                errorText = name.errorText,
                onTextChanged = nameTextChanged
            )
            TextFieldRow(
                title = stringResource(id = R.string.amount),
                value = amount.text,
                error = amount.error,
                errorText = amount.errorText,
                onTextChanged = amountTextChanged,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            DueRow(
                title = stringResource(id = R.string.due),
                value = due,
                onCalenderClicked = { calenderVisibility = true }
            )
            DropdownMenuRow(
                title = stringResource(id = R.string.category),
                value = category,
                options = Category.entries.map { it.name },
                onItemSelected = categoryTextChanged,
            )
        }

    }
    if (calenderVisibility) {
        Calendar(
            onDismiss = { calenderVisibility = false },
            dateSelected = dueTextChanged
        )
    }
}




