package com.example.spendwise.domain.repository

import com.example.spendwise.domain.models.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {

    fun fetchUserPreferences(): Flow<UserPreferences>

    suspend fun updateUserPreferences(amount: Float)
}