package com.example.personalcostdashboard

import android.app.Application
import com.example.personalcostdashboard.data.AppDatabase
import com.example.personalcostdashboard.data.repository.ExpenseRepository

class PersonalCostDashboardApp : Application() {

    // Lazy initialization of the Room database
    val database by lazy { AppDatabase.getDatabase(this) }

    // Lazy initialization of your repository
    val repository by lazy { ExpenseRepository(database.expenseDao()) }
}
