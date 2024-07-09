package com.example.spendwise.presentation.add_edit

sealed class AddEditEvent {
    data class OnTitleChange(val value: String): AddEditEvent()
    data class OnAmountChange(val value: String): AddEditEvent()
    data class OnDueChange(val value: Long): AddEditEvent()
    data class OnCategoryChange(val value: String): AddEditEvent()
    data object InsertSpendingEvent: AddEditEvent()
    data object DeleteSpendingEvent: AddEditEvent()

}