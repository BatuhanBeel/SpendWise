package com.example.spendwise.domain.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity(tableName = "expenses_table")
data class Spending(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val due: Long,
    val amount: Float,
    val category: String,
    val color: Int
)
