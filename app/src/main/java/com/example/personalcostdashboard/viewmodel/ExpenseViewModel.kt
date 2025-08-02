package com.example.personalcostdashboard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.personalcostdashboard.data.AppDatabase
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository
    val allExpenses: LiveData<List<Expense>>

    init {
        // Build the repository using the database's DAO
        val expenseDao = AppDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        // Expose all expenses as LiveData to the UI
        allExpenses = repository.getAllExpenses()
    }

    fun insertExpense(expense: Expense) {
        // Launch a coroutine to insert on a background thread
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        // Launch a coroutine to remove the expense
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        // Launch a coroutine to update the entry
        viewModelScope.launch {
            repository.updateExpense(expense)
        }
    }
}
