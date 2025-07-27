package com.example.personalcostdashboard.ui.dashboard.viewmodel

import androidx.lifecycle.*
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DashboardViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _monthlyBudget = MutableLiveData<Double>(2500.0) // Static value for now
    val monthlyBudget: LiveData<Double> = _monthlyBudget

    private val _monthSpent = MutableLiveData<Double>()
    val monthSpent: LiveData<Double> = _monthSpent

    val recentExpenses: LiveData<List<Expense>> = repository.getRecentExpenses(limit = 5)

    private val _remainingBudget = MediatorLiveData<Double>()
    val remainingBudget: LiveData<Double> = _remainingBudget

    private val _progressPercent = MediatorLiveData<String>()
    val progressPercent: LiveData<String> = _progressPercent

    init {
        // Set up LiveData chaining
        _remainingBudget.addSource(_monthlyBudget) { updateRemainingBudget() }
        _remainingBudget.addSource(_monthSpent) { updateRemainingBudget() }

        _progressPercent.addSource(_monthlyBudget) { updateProgress() }
        _progressPercent.addSource(_monthSpent) { updateProgress() }

        calculateMonthlySpent()
    }

    private fun updateRemainingBudget() {
        val budget = _monthlyBudget.value ?: 0.0
        val spent = _monthSpent.value ?: 0.0
        _remainingBudget.value = budget - spent
    }

    private fun updateProgress() {
        val spent = _monthSpent.value ?: 0.0
        val budget = _monthlyBudget.value ?: 1.0 // avoid div by zero
        val percent = (spent / budget * 100).coerceAtMost(100.0)
        _progressPercent.value = "${percent.toInt()}%"
    }

    private fun calculateMonthlySpent() {
        val currentMonth = SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date())

        repository.getAllExpenses().observeForever { expenses ->
            val monthlyExpenses = expenses.filter {
                it.dateString.startsWith(currentMonth)
            }
            _monthSpent.value = monthlyExpenses.sumOf { it.amount }
        }
    }
}
