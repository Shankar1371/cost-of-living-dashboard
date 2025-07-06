package com.example.personalcostdashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.personalcostdashboard.ui.AddExpense.AddExpenseViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class AddExpenseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun setDate_updatesSelectedDate() {
        val viewModel = AddExpenseViewModel(repository)
        viewModel.setDate("1/1/2025")
        assertEquals("1/1/2025", viewModel.selectedDate.value)
    }
}
