package com.example.spendwise.domain.repository

import com.example.spendwise.domain.models.Spending
import kotlinx.coroutines.flow.Flow

interface SpendingRepository {

    fun getAllItem(): Flow<List<Spending>>

    suspend fun getSpecificItem(id: Int): Spending

    fun getAllItemWithFilter(category: String): Flow<List<Spending>>

    suspend fun insertItem(item: Spending)

    suspend fun deleteAllItem()

    suspend fun deleteItem(item: Spending)
}