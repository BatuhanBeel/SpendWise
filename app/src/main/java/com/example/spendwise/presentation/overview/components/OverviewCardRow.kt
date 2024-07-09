package com.example.spendwise.presentation.overview.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.spendwise.presentation.components.AmountDecimalFormat

@Composable
fun OverviewCardRow(
    name: String,
    amount: Float,
    isNegative: Boolean,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal
) {
    val amountState by remember(amount){
        mutableStateOf(AmountDecimalFormat.format(amount))
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.45f),
            text = name,
            fontWeight = fontWeight,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(modifier = Modifier.weight(0.55f),
            text = if (isNegative) "-$${amountState}" else " $$amountState",
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = fontWeight,
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}