package com.example.spendwise.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SpendingIndicator(color: Color, modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .size(4.dp, 36.dp)
            .background(color = color)
    )
}