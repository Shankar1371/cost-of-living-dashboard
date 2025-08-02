package com.example.personalcostdashboard.data.repository

import androidx.lifecycle.LiveData
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.ExpenseDao

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Fetch all expenses as LiveData
    fun getAllExpenses(): LiveData<List<Expense>> {
        // Delegate the query to the DAO
        return expenseDao.getAllExpenses()
    }

    // Insert a new expense into the database
    suspend fun insertExpense(expense: Expense) {
        // Pass the entity to the DAO to save it
        expenseDao.insertExpense(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        // Update the given expense in the database
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        // Remove the expense from persistent storage
        expenseDao.deleteExpense(expense)
    }

    // Optional: Delete all expenses
    suspend fun deleteAllExpenses() {
        // Clear the entire expenses table
        expenseDao.deleteAll()
    }
}
