package com.example.personalcostdashboard.ui.Dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import com.example.personalcostdashboard.ui.dashboard.viewmodel.DashboardViewModel

class DashboardViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return _root_ide_package_.com.example.personalcostdashboard.ui.dashboard.viewmodel.DashboardViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
