package com.example.personalcostdashboard.ui.History

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.ExpenseRepository

class HistoryViewModel : ViewModel() {

    val expenses: LiveData<List<Expense>> = ExpenseRepository.getExpenses()
}