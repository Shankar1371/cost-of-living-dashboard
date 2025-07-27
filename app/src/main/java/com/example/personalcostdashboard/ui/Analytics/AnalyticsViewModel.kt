package com.example.personalcostdashboard.ui.analytics.viewmodel

import androidx.lifecycle.*
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import com.example.personalcostdashboard.ui.analytics.model.AnalyticsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AnalyticsViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _analyticsData = MutableLiveData<AnalyticsData>()
    val analyticsData: LiveData<AnalyticsData> = _analyticsData

    init {
        // Observe all expenses from Room
        repository.getAllExpenses().observeForever { expenses ->
            calculateAnalytics(expenses)
        }
    }

    private fun calculateAnalytics(expenses: List<Expense>) {
        val totalSpent = expenses.sumOf { it.amount }
        val averageDaily = if (expenses.isNotEmpty()) totalSpent / 30 else 0.0
        val totalSaved = 250.0 // You can later calculate from income

        val categoryWise = expenses.groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }

        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
        val monthlySpend = expenses.groupBy {
            try {
                val parsedDate = inputFormat.parse(it.dateString)
                monthFormat.format(parsedDate ?: Date())
            } catch (e: Exception) {
                "Unknown"
            }
        }.mapValues { entry -> entry.value.sumOf { it.amount } }

        val insight = when {
            totalSpent == 0.0 -> "Add some expenses to see personalized insights"
            totalSpent > 2000 -> "High spending detected — check your biggest categories."
            averageDaily < 20 -> "You're managing your daily expenses well!"
            else -> "You're doing fine. Keep tracking your expenses."
        }

        _analyticsData.value = AnalyticsData(
            totalSpent = totalSpent,
            totalSaved = totalSaved,
            averageDaily = averageDaily,
            categoryWise = categoryWise,
            monthlySpend = monthlySpend,
            insightText = insight
        )
    }
}
