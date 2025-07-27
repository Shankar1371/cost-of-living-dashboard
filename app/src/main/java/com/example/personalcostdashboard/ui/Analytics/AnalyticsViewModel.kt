package com.example.personalcostdashboard.ui.analytics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalcostdashboard.ui.analytics.model.AnalyticsData

class AnalyticsViewModel : ViewModel() {

    private val _analyticsData = MutableLiveData<AnalyticsData>()
    val analyticsData: LiveData<AnalyticsData> = _analyticsData

    init {
        loadMockAnalyticsData()
    }

    private fun loadMockAnalyticsData() {
        // Category-wise spending (for pie chart)
        val categoryWise = mapOf(
            "Groceries" to 400.0,
            "Transport" to 180.0,
            "Rent" to 600.0,
            "Utilities" to 100.0,
            "Other" to 70.0
        )

        // Monthly spending (for line chart)
        val monthlySpend = mapOf(
            "Jan" to 1100.0,
            "Feb" to 1300.0,
            "Mar" to 1200.0,
            "Apr" to 1400.0,
            "May" to 1350.0,
            "Jun" to 1250.0
        )

        // Compute totals
        val totalSpent = categoryWise.values.sum()
        val totalSaved = 250.0 // Placeholder; you can change this dynamically later
        val averageDaily = totalSpent / 30  // Assuming a 30-day average

        // Update LiveData with new analytics
        val analytics = AnalyticsData(
            totalSpent = totalSpent,
            totalSaved = totalSaved,
            averageDaily = averageDaily,
            categoryWise = categoryWise,
            monthlySpend = monthlySpend
        )

        _analyticsData.value = analytics
    }

    // Optional: to allow manual updates later
    fun updateAnalytics(data: AnalyticsData) {
        _analyticsData.value = data
    }
}
