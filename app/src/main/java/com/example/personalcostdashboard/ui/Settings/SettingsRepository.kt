package com.example.personalcostdashboard.ui.Settings

import androidx.lifecycle.LiveData

class SettingsRepository(private val settingsDao: SettingsDao) {
    val settings: LiveData<UserSettings?> = settingsDao.getSettings()

    suspend fun saveSettings(newSettings: UserSettings) {
        // Save or update the user's settings in the database
        settingsDao.saveSettings(newSettings)
    }
}
