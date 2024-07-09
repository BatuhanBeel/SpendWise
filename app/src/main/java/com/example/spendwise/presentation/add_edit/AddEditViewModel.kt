package com.example.spendwise.presentation.add_edit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.domain.repository.SpendingRepository
import com.example.spendwise.presentation.add_edit.components.AMOUNT_MAX_CHAR_COUNT
import com.example.spendwise.presentation.add_edit.components.NAME_MAX_CHAR_COUNT
import com.example.spendwise.presentation.util.determineColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.Calendar

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: SpendingRepository
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var spendingItem = mutableStateOf<Spending?>(null)

    private val _nameState = mutableStateOf(SpendingTextFieldState())
    val nameState: State<SpendingTextFieldState> = _nameState

    private val _amountState = mutableStateOf(SpendingTextFieldState())
    val amountState: State<SpendingTextFieldState> = _amountState

    private val _dueState = mutableLongStateOf(Calendar.getInstance().timeInMillis)
    val dueState: State<Long> = _dueState

    private val _categoryState = mutableStateOf("Food")
    val categoryState: State<String> = _categoryState

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.OnTitleChange -> {
                if (event.value.length <= NAME_MAX_CHAR_COUNT) {
                    _nameState.value = _nameState.value.copy(
                        text = event.value,
                        error = false
                    )
                } else {
                    _nameState.value = _nameState.value.copy(
                        errorText = "Name length can't be higher than 15.",
                        error = true
                    )
                }
            }

            is AddEditEvent.OnAmountChange -> {
                if ((event.value.isEmpty() || event.value.toFloatOrNull() != null)
                    && event.value.length <= AMOUNT_MAX_CHAR_COUNT) {
                    _amountState.value = amountState.value.copy(
                        text = event.value,
                        error = false
                    )
                } else if(event.value.length > AMOUNT_MAX_CHAR_COUNT){
                    _amountState.value = amountState.value.copy(
                        error = true,
                        errorText = "Amount length can't be higher than 10."
                    )
                }
                else{
                    Log.d("AddViewModel", event.value.length.toString())
                    _amountState.value = amountState.value.copy(
                        error = true,
                        errorText = "Please enter value in correct form."
                    )
                }
            }

            is AddEditEvent.OnDueChange -> {
                _dueState.longValue = event.value
            }

            is AddEditEvent.OnCategoryChange -> {
                _categoryState.value = event.value
            }

            is AddEditEvent.InsertSpendingEvent -> {
                val isValidForTitle = checkValidForName(nameState.value.text)
                val isValidForAmount = checkValidForAmount(amountState.value.text)
                if (isValidForTitle && isValidForAmount) {
                    insertItem()
                }
            }

            is AddEditEvent.DeleteSpendingEvent -> {
                deleteItem()
            }
        }
    }

    private fun checkValidForName(name: String): Boolean {
        return if (name.isEmpty()) {
            _nameState.value = _nameState.value.copy(
                error = true,
                errorText = "Name can't be empty."
            )
            false
        } else {
            true
        }
    }

    private fun checkValidForAmount(amount: String): Boolean {
        if (amount.toFloatOrNull() == null) {
            _amountState.value = amountState.value.copy(
                error = true,
                errorText = "Please enter float number."
            )
            return false
        } else if (amount.toFloat() <= 0) {
            _amountState.value = amountState.value.copy(
                error = true,
                errorText = "Must be higher than 0."
            )
            return false
        } else {
            _amountState.value = amountState.value.copy(
                text = amount,
                error = false
            )
            return true
        }
    }

    private fun insertItem() {
        viewModelScope.launch {
            try {
                repository.insertItem(
                    Spending(
                        id = if (spendingItem.value != null) spendingItem.value!!.id else 0,
                        name = nameState.value.text,
                        amount = amountState.value.text.toFloat(),
                        due = dueState.value,
                        category = categoryState.value,
                        color = determineColor(categoryState.value)
                    )
                )
                if (spendingItem.value != null){
                    _uiEvent.emit(UiEvent.ShowSnackBar("Spending successfully changed."))
                    _uiEvent.emit(UiEvent.NavigateUp)
                }
                else{
                    clearInput()
                    _uiEvent.emit(UiEvent.ShowSnackBar("Spending successfully added."))
                }
            } catch (e: Exception) {
                _uiEvent.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save spending."
                    )
                )
            }
        }
    }

    private fun deleteItem(){
        viewModelScope.launch(Dispatchers.IO) {
            spendingItem.value?.let {
                repository.deleteItem(it)
                _uiEvent.emit(UiEvent.ShowSnackBar("Spending deleted."))
                _uiEvent.emit(UiEvent.NavigateUp)
            }
        }
    }

    fun findItem(itemId: Int) {
        viewModelScope.launch {
            val item = repository.getSpecificItem(itemId)
            spendingItem.value = item
            _nameState.value = nameState.value.copy(
                text = item.name
            )
            _amountState.value = amountState.value.copy(
                text = item.amount.toString()
            )
            _dueState.longValue = item.due
            _categoryState.value = item.category
        }
    }

    private fun clearInput() {
        _nameState.value = nameState.value.copy(
            text = ""
        )
        _amountState.value = amountState.value.copy(
            text = ""
        )
        _dueState.longValue = Calendar.getInstance().timeInMillis
        _categoryState.value = "Food"
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        data object NavigateUp: UiEvent()
    }
}

