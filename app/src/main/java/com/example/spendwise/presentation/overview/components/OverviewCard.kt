package com.example.spendwise.presentation.overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.spendwise.R
import kotlin.math.abs


@Composable
fun OverviewCard(
    balance: Float,
    expenses: Float,
    modifier: Modifier = Modifier
) {
    var openAlertDialog by remember {
        mutableStateOf(false)
    }

    val remaining by remember(balance, expenses) {
        mutableFloatStateOf(balance - expenses)
    }

    Card(
        modifier = modifier
            .padding(top = 16.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OverviewCardRow(
                name = stringResource(id = R.string.balance),
                amount = balance,
                isNegative = false
            )
            OverviewCardRow(
                name = stringResource(id = R.string.expenses),
                amount = expenses,
                isNegative = true
            )
            Divider(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colorScheme.surfaceTint
            )
            OverviewCardRow(
                name = stringResource(id = R.string.remaining),
                amount = abs(remaining),
                isNegative = if (remaining < 0) true else false,
                fontWeight = FontWeight.Bold
            )
            TextButton(
                modifier = Modifier
                    .align(Alignment.End),
                onClick = {
                    openAlertDialog = true
                }
            ) {
                Text(
                    text = stringResource(id = R.string.change_balance),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
    if (openAlertDialog) {
        OverviewDialog(
            title = stringResource(id = R.string.change_balance),
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = {
                openAlertDialog = false
            }
        )
    }
}