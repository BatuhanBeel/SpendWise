package com.example.spendwise.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Train
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.spendwise.domain.models.Category

fun getCategoryIcon(category: String): ImageVector {
    when (category) {
        Category.Food.name -> {
            return Icons.Filled.Fastfood
        }

        Category.Shopping.name -> {
            return Icons.Filled.ShoppingCart
        }

        Category.Entertainment.name -> {
            return Icons.Filled.CrueltyFree
        }

        Category.Health.name -> {
            return Icons.Filled.Healing
        }

        Category.Travel.name -> {
            return Icons.Filled.Train
        }

        else -> {
            return Icons.Filled.MoreHoriz
        }
    }
}