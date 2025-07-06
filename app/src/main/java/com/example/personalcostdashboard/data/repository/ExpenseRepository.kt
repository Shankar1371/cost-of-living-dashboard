package com.example.personalcostdashboard.data.repository

import androidx.lifecycle.LiveData
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.ExpenseDao

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Fetch all expenses as LiveData
    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    // Insert a new expense into the database
    suspend fun insertExpense(expense: Expense) {
        expenseDao.insertExpense(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }

    // Optional: Delete all expenses
    suspend fun deleteAllExpenses() {
        expenseDao.deleteAll()
    }
}
