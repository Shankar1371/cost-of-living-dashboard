package com.example.personalcostdashboard.ui.History

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.personalcostdashboard.data.AppDatabase
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ExpenseRepository
    val expenses: LiveData<List<Expense>>

    init {
        val dao = AppDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(dao)
        expenses = repository.getAllExpenses()
    }
}