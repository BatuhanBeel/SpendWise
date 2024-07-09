package com.example.spendwise.presentation.overview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.repository.SpendingRepository
import com.example.spendwise.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val repository: SpendingRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _overviewState = mutableStateOf(OverviewState())
    val overviewState = _overviewState

    fun fetchInitialData(){
        getAllSpending()
        fetchUserPreferences()
    }

    private fun getAllSpending() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllItem().collect {
                _overviewState.value = _overviewState.value.copy(
                    spendingList = it
                )
            }
        }
    }

    private fun fetchUserPreferences(){
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.fetchUserPreferences().collect { preferences ->
                _overviewState.value = _overviewState.value.copy(
                    userAmountPreferences = preferences.amount
                )
            }
        }
    }

    fun changeUserAmountPreferences(amount: Float){
        viewModelScope.launch {
            userPreferencesRepository.updateUserPreferences(amount)
        }
    }
}