package com.example.spendwise.presentation.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.spendwise.domain.repository.UserPreferencesRepository
import com.example.spendwise.presentation.add_edit.SpendingTextFieldState
import com.example.spendwise.presentation.add_edit.components.AMOUNT_MAX_CHAR_COUNT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewDialogViewModel @Inject constructor(
    private val balancePreferences: UserPreferencesRepository
): ViewModel() {

    var balanceTextFieldState by mutableStateOf(SpendingTextFieldState())

    fun onBalanceChanged(balance: String){
        if ((balance.isEmpty() || balance.toFloatOrNull() != null)
            && balance.length <= AMOUNT_MAX_CHAR_COUNT
        ) {
            balanceTextFieldState = balanceTextFieldState.copy(
                text = balance,
                error = false
            )
        } else if(balance.length > AMOUNT_MAX_CHAR_COUNT){
            balanceTextFieldState = balanceTextFieldState.copy(
                error = true,
                errorText = "Amount length can't be higher than 10."
            )
        }
        else{
            balanceTextFieldState = balanceTextFieldState.copy(
                error = true,
                errorText = "Please enter value in correct form."
            )
        }
    }

    suspend fun isInputValid(): Boolean {
            val isValidForAmount = checkValidForAmount(balanceTextFieldState.text)
            if (isValidForAmount){
                balancePreferences.updateUserPreferences(balanceTextFieldState.text.toFloat())
                return true
            }else {
                return false
            }
    }

    private fun checkValidForAmount(amount: String): Boolean {
        if (amount.toFloatOrNull() == null) {
            balanceTextFieldState = balanceTextFieldState.copy(
                error = true,
                errorText = "Please enter float number."
            )
            return false
        } else if (amount.toFloat() < 0) {
            balanceTextFieldState = balanceTextFieldState.copy(
                error = true,
                errorText = "Must be higher than 0."
            )
            return false
        } else {
            balanceTextFieldState = balanceTextFieldState.copy(
                text = amount,
                error = false
            )
            return true
        }
    }

}