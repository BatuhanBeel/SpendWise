package com.example.spendwise.presentation.add_edit.edit

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spendwise.R
import com.example.spendwise.presentation.add_edit.AddEditEvent
import com.example.spendwise.presentation.add_edit.AddEditViewModel
import com.example.spendwise.presentation.add_edit.components.AddEditBody
import com.example.spendwise.presentation.components.SpendWiseDialog
import kotlinx.coroutines.flow.collectLatest


@Composable
fun EditScreen(
    argumentData: Int,
    viewModel: AddEditViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    navigateUp: () -> Unit,
){
    LaunchedEffect(argumentData) {
        viewModel.findItem(argumentData)
    }
    LaunchedEffect(true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is AddEditViewModel.UiEvent.ShowSnackBar ->{
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddEditViewModel.UiEvent.NavigateUp ->{
                    navigateUp()
                }
            }
        }
    }

    val nameState = viewModel.nameState.value
    val amountState = viewModel.amountState.value
    val dueState = viewModel.dueState.value
    val categoryState = viewModel.categoryState.value

    var alertDialogIsVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            onClick = { viewModel.onEvent(AddEditEvent.DeleteSpendingEvent) }) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(id = R.string.delete))
        }
        AddEditBody(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
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
                .align(Alignment.End)
                .padding(36.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = CircleShape,
            onClick = {
                viewModel.onEvent(AddEditEvent.InsertSpendingEvent)
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(id = R.string.edit)
            )
        }
        if (alertDialogIsVisible){
            SpendWiseDialog(
                title = stringResource(id = R.string.delete),
                text = "Do you want to delete expending?",
                icon = Icons.Filled.Clear,
                onDismissRequest = { alertDialogIsVisible = false },
                onConfirmation = {
                    alertDialogIsVisible = false
                    viewModel.onEvent(AddEditEvent.DeleteSpendingEvent)
                }
            )
        }
    }
}