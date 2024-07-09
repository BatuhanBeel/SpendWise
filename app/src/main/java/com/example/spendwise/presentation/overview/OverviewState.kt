package com.example.spendwise.presentation.overview

import com.example.spendwise.domain.models.Spending

data class OverviewState(
    val spendingList: List<Spending> = emptyList(),
    val userAmountPreferences: Float = 0F
)
