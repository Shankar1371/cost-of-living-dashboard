package com.example.personalcostdashboard.ui.Settings

import androidx.lifecycle.LiveData

class SettingsRepository(private val settingsDao: SettingsDao) {
    val settings: LiveData<UserSettings?> = settingsDao.getSettings()

    suspend fun saveSettings(newSettings: UserSettings) {
        settingsDao.saveSettings(newSettings)
    }
}
