package com.example.personalcostdashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import com.example.personalcostdashboard.ui.addexpense.AddExpenseViewModel
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddExpenseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: ExpenseRepository
    private lateinit var viewModel: AddExpenseViewModel

    @Before
    fun setUp() {
        // Creating a mock repository
        repository = mockk(relaxed = true)  // relaxed allows default values
        viewModel = AddExpenseViewModel(repository)
    }

    @Test
    fun setDate_updatesSelectedDate() {
        viewModel.setDate("1/1/2025")
        assertEquals("1/1/2025", viewModel.selectedDate.value)
    }
}
