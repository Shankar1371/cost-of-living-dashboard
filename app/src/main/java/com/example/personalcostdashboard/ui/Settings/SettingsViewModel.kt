package com.example.personalcostdashboard.ui.Settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.personalcostdashboard.data.AppDatabase
import kotlinx.coroutines.launch
class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SettingsRepository(AppDatabase.getDatabase(application).settingsDao())
    val settings: LiveData<UserSettings?> = repository.settings

    fun saveSettings(updatedSettings: UserSettings) = viewModelScope.launch {
        repository.saveSettings(updatedSettings)
    }
}
