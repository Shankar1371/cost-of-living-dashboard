package com.example.personalcostdashboard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses ORDER BY dateString DESC")
    fun getAllExpenses(): LiveData<List<Expense>> // Observe all expenses sorted by date

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense) // Add a new expense or replace if it exists

    @Update
    suspend fun updateExpense(expense: Expense) // Modify an existing expense entry

    @Delete
    suspend fun deleteExpense(expense: Expense) // Remove a specific expense

    @Query("DELETE FROM expenses")
    suspend fun deleteAll() // Clear all expense records
}
