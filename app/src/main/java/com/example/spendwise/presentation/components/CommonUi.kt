package com.example.spendwise.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendwise.R
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.presentation.util.convertTimeToString
import com.example.spendwise.ui.theme.SpendWiseTheme
import java.text.DecimalFormat

@Composable
fun SpendingRow(
    item: Spending,
    editable: Boolean,
    modifier: Modifier = Modifier,
    editOnClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .heightIn(min = 68.dp)
            .padding(start = 16.dp)
            .clearAndSetSemantics {
                contentDescription =
                    "A transaction worth ${item.amount} was made on ${item.due} for ${item.name}."
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpendingIndicator(Color.Red)
        Spacer(
            modifier = Modifier
                .width(12.dp)
        )
        Column {
            Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = convertTimeToString(item.due), style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = "-$${AmountDecimalFormat.format(item.amount)}",
            style = MaterialTheme.typography.bodyLarge
        )
        if (editable) {
            IconButton(
                onClick = { editOnClick(item.id) })
            {
                Icon(
                    modifier = Modifier.padding(10.dp),
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit item",
                )
            }
        }
    }
    Divider(color = MaterialTheme.colorScheme.outlineVariant)
}

@Composable
fun SeeAllButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.see_all),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpendingRowPreview() {
    SpendWiseTheme {
        SpendingRow(
            item = Spending(1, "Food", 1561651, 300F, "Food", 0x0FFFFFF),
            editable = true,
            editOnClick = {}
        )
    }
}

val AmountDecimalFormat = DecimalFormat("#,###.##")
