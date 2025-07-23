package com.example.personalcostdashboard.ui.analytics.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalcostdashboard.ui.analytics.model.AnalyticsData

class AnalyticsViewModel : ViewModel() {

    private val _analyticsData = MutableLiveData<AnalyticsData>()
    val analyticsData: LiveData<AnalyticsData> = _analyticsData

    init {
        loadMockData()
    }

    private fun loadMockData() {
        val data = AnalyticsData(
            totalSpent = 1350.0,
            totalSaved = 250.0,
            averageDaily = 45.0,
            categoryWise = mapOf(
                "Groceries" to 400.0,
                "Transport" to 180.0,
                "Rent" to 600.0,
                "Utilities" to 100.0,
                "Other" to 70.0
            ),
            monthlySpend = mapOf(
                "Jan" to 1100.0,
                "Feb" to 1300.0,
                "Mar" to 1200.0,
                "Apr" to 1400.0,
                "May" to 1350.0,
                "Jun" to 1250.0
            )
        )

        _analyticsData.value = data
    }
}
