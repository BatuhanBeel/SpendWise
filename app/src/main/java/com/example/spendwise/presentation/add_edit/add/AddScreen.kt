package com.example.spendwise.presentation.add_edit.add

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.R
import com.example.spendwise.presentation.add_edit.AddEditEvent
import com.example.spendwise.presentation.add_edit.components.AddEditBody
import com.example.spendwise.presentation.add_edit.AddEditViewModel
import com.example.spendwise.ui.theme.SpendWiseTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddScreen(
    viewModel: AddEditViewModel = hiltViewModel<AddEditViewModel>(),
    context: Context = LocalContext.current
) {
    val nameState = viewModel.nameState.value
    val amountState = viewModel.amountState.value
    val dueState = viewModel.dueState.value
    val categoryState = viewModel.categoryState.value

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is AddEditViewModel.UiEvent.ShowSnackBar ->{
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                else ->{  }
            }
        }
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        AddEditBody(
            name = nameState,
            amount = amountState,
            due = dueState,
            category = categoryState,
            nameTextChanged = {
                viewModel.onEvent(AddEditEvent.OnTitleChange(it))
            },
            amountTextChanged = {
                viewModel.onEvent(AddEditEvent.OnAmountChange(it))
            },
            dueTextChanged = {
                viewModel.onEvent(AddEditEvent.OnDueChange(it))
            },
            categoryTextChanged = {
                viewModel.onEvent(AddEditEvent.OnCategoryChange(it))
            }
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(36.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            onClick = {
                viewModel.onEvent(AddEditEvent.InsertSpendingEvent)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add)
            )
        }
    }
}


@Preview
@Composable
private fun AddScreenPreview() {
    SpendWiseTheme {
        Surface {
            AddScreen()
        }
    }
}