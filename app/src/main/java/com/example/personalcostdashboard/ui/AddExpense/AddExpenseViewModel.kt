package com.example.personalcostdashboard.ui.addexpense

import androidx.lifecycle.*
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    val expenses = repository.getAllExpenses()  // LiveData<List<Expense>>

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    fun saveExpense(amount: Double, category: String, description: String, dateString: String) {
        val expense = Expense(
            amount = amount,
            category = category,
            description = description,
            datestring = dateString
        )

        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

}
