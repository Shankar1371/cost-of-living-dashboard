package com.example.personalcostdashboard.ui.AddExpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddExpenseViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    fun saveExpense(amount: String, category: String, description: String, date: String) {
        // Replace with DB insert logic later
        println("Saved expense: $amount | $category | $description | $date")
    }
}