package com.example.personalcostdashboard.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalcostdashboard.model.Expense
import com.example.personalcostdashboard.ui.History.Expense

class HistoryViewModel : ViewModel() {
    private val _expenses = MutableLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> get() = _expenses

    init {
        loadDummyExpenses()
    }

    private fun loadDummyExpenses() {
        _expenses.value = listOf(
            Expense("Groceries", 50.0, "Food", "2025-06-21"),
            Expense("Internet", 35.0, "Utilities", "2025-06-20"),
            Expense("Movie", 12.0, "Entertainment", "2025-06-18")
        )
    }
}
