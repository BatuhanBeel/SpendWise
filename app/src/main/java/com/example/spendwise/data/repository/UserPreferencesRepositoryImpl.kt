package com.example.spendwise.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.spendwise.domain.repository.UserPreferencesRepository
import com.example.spendwise.data.util.PreferencesKeys
import com.example.spendwise.domain.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): UserPreferencesRepository {

    override fun fetchUserPreferences(): Flow<UserPreferences> =
        dataStore.data.map { preferences ->
            val amount = preferences[PreferencesKeys.AMOUNT] ?: 0F
            UserPreferences(amount)
        }


    override suspend fun updateUserPreferences(amount: Float) {
        try {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.AMOUNT] = amount
            }
        } catch (e: IOException) {
            Log.d("DataStore", e.message.toString())
        }
    }
}