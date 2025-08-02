package com.example.personalcostdashboard.ui.Dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        // Default message displayed on the dashboard
        value = "This is home Fragment"
    }
    // Publicly exposed immutable LiveData
    val text: LiveData<String> = _text
}