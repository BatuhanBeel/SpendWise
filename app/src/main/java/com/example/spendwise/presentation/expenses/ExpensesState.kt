package com.example.spendwise.presentation.expenses

import com.example.spendwise.domain.models.Spending

data class ExpensesState(
    val allExpensesList: List<Spending> = emptyList()
)
