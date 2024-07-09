package com.example.spendwise.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.spendwise.domain.models.Spending

data class CircleUiElements(
    val amounts: Float,
    val proportions: List<Float>,
    val colors: List<Color>
)

@Composable
fun CircleBody(
    items: List<Spending>,
    circleLabel: String,
    modifier: Modifier = Modifier
) {
    val uiState by produceState(key1 = items, initialValue = CircleUiElements(0F, listOf(), listOf())) {
        val amountsTotal = items.map { item -> item.amount }.sum()
        val proportions = extractProportionList(items)
        val colors =  items.map { item -> Color(item.color) }
        value = CircleUiElements(amountsTotal, proportions, colors)
    }

    Column {
        Box(modifier = modifier.padding(16.dp)) {
            AnimatedCircle(
                uiState.proportions,
                uiState.colors,
                Modifier
                    .height(250.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = circleLabel,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "$${AmountDecimalFormat.format(uiState.amounts)}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

fun extractProportionList(items: List<Spending>): List<Float>{
    return items.map { item -> item.amount / items.map { totalItem -> totalItem.amount }.sum() }
}
