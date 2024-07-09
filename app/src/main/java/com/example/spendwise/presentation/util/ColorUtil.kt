package com.example.spendwise.presentation.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.spendwise.domain.models.Category
import com.example.spendwise.ui.theme.entertainmentCategory
import com.example.spendwise.ui.theme.foodCategory
import com.example.spendwise.ui.theme.healthCategory
import com.example.spendwise.ui.theme.otherCategory
import com.example.spendwise.ui.theme.shoppingCategory
import com.example.spendwise.ui.theme.travelCategory

fun determineColor(category: String): Int {
    return when (category) {
        Category.Food.name -> foodCategory.toArgb()
        Category.Shopping.name -> shoppingCategory.toArgb()
        Category.Entertainment.name -> entertainmentCategory.toArgb()
        Category.Health.name -> healthCategory.toArgb()
        Category.Travel.name -> travelCategory.toArgb()
        Category.Other.name -> otherCategory.toArgb()
        else -> {
            Color.White.toArgb()
        }
    }
}