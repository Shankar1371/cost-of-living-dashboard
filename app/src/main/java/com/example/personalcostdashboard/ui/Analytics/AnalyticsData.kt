package com.example.personalcostdashboard.ui.analytics.model

data class AnalyticsData(
    val totalSpent: Double,
    val totalSaved: Double,
    val averageDaily: Double,
    val categoryWise: Map<String, Double>,
    val monthlySpend: Map<String, Double>
)
