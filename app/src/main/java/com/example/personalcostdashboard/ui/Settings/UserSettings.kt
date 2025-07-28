package com.example.personalcostdashboard.ui.Settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class UserSettings(
    @PrimaryKey val id: Int = 1,
    val monthlyBudget: Double,
    val currencySymbol: String,
    val isDarkMode: Boolean,
    val notificationsEnabled: Boolean
)
