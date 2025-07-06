package com.example.personalcostdashboard.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object ExpenseRepository {

    private val expenseList = mutableListOf<Expense>()
    private val expensesLiveData = MutableLiveData<List<Expense>>(expenseList)

    fun getExpenses(): LiveData<List<Expense>> = expensesLiveData

    fun addExpense(expense: Expense) {
        expenseList.add(expense)
        expensesLiveData.value = expenseList.toList()
    }
}
