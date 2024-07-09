package com.example.spendwise.presentation.add_edit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.spendwise.presentation.util.convertTimeToString

@Composable
fun DueRow(
    title: String,
    value: Long,
    onCalenderClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = convertTimeToString(value),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    onCalenderClicked()
                },
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = "Calendar",
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}