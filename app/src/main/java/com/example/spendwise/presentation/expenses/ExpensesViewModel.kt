package com.example.spendwise.presentation.expenses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    val repository: SpendingRepository
): ViewModel() {

    private val _expensesState = mutableStateOf(ExpensesState())
    val expensesState: State<ExpensesState> = _expensesState


    fun getAllItem(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllItem().collect{
                _expensesState.value = _expensesState.value.copy(
                    allExpensesList = it
                )
            }
        }
    }

    fun clearAllItem(){
        viewModelScope.launch {
            repository.deleteAllItem()
        }
    }
}