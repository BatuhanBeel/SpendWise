package com.example.spendwise.presentation.expenses.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.presentation.components.AmountDecimalFormat
import com.example.spendwise.presentation.components.SeeAllButton
import com.example.spendwise.presentation.components.SpendingIndicator
import com.example.spendwise.presentation.components.SpendingRow
import com.example.spendwise.ui.theme.SpendWiseTheme
import com.example.spendwise.ui.theme.foodCategory

@Composable
fun <T>CategoryCard(
    title: String,
    color: Color,
    icon: ImageVector,
    amount: Float,
    data: List<T>,
    modifier: Modifier = Modifier,
    onClickSeeAll: () -> Unit,
    expensesRows: @Composable (T) -> Unit,
) {
    var expanded by remember{ mutableStateOf(false) }
    Card(modifier = modifier
        .padding(4.dp)) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .heightIn(min = 56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpendingIndicator(color = color, modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = icon, tint = color, contentDescription = "$title icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.weight(1F))
                Text(text = "$ ${AmountDecimalFormat.format(amount)}", color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.titleLarge)
                if (data.isNotEmpty()){
                    Icon(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable { expanded = !expanded },
                        imageVector = if (expanded) Icons.Outlined.ExpandMore else Icons.Outlined.ChevronRight,
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "Expand"
                    )
                }
                else{
                    Spacer(modifier = Modifier.padding(end = 36.dp))
                }
            }
            if (expanded){
                Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceTint, modifier = modifier)
                data.take(SHOWN_ITEMS_COUNT).forEach { item ->
                    expensesRows(item)
                }
                if (SHOWN_ITEMS_COUNT < data.size){
                    SeeAllButton(onClick = onClickSeeAll)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    SpendWiseTheme {
        CategoryCard(
            title = "Food",
            color = foodCategory,
            icon = Icons.Filled.Fastfood,
            amount = 100F,
            data = listOf<Spending>(),
            onClickSeeAll = { /*TODO*/ },
        ) {
            SpendingRow(
                item = Spending(it.id, it.name, it.due, it.amount, it.category, it.color),
                editable = true,
                editOnClick = {}
            )
        }
    }
}

const val SHOWN_ITEMS_COUNT = 3