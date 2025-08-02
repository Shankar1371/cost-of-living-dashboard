package com.example.personalcostdashboard.ui.addexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

class AddExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    fun setDate(date: String) {
        // Update the currently chosen date
        _selectedDate.value = date
    }

    fun saveExpense(amount: Double, category: String, description: String, dateString: String) {
        // Create an Expense entity from the provided form values
        val expense = Expense(
            amount = amount,
            category = category,
            dateString = dateString,
            description = description
        )
        // Insert the expense on a background thread
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }
}