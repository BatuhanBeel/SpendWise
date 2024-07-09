package com.example.spendwise.presentation.detail

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.models.Spending
import com.example.spendwise.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: SpendingRepository
): ViewModel() {

    private val _detailList = mutableStateListOf<Spending>()
    val detailList = _detailList


    fun getAllItem(category: String){
        viewModelScope.launch(Dispatchers.IO){
            if (category != "All"){
                repository.getAllItemWithFilter(category).collect {
                    _detailList.clear()
                    _detailList.addAll(it)
                    Log.d("DetailViewModel", "${detailList.toList()}")
                }
            } else{
                repository.getAllItem().collect{
                    _detailList.clear()
                    _detailList.addAll(it)
                }
            }
        }
    }
}