package com.example.personalcostdashboard.ui.addexpense

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personalcostdashboard.data.AppDatabase
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import com.example.personalcostdashboard.databinding.FragmentAddExpenseBinding

import java.util.*

class AddExpenseFragment : Fragment() {

    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AddExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)

        val database = AppDatabase.getDatabase(requireContext().applicationContext)
        val repository = ExpenseRepository(database.expenseDao())
        val factory = AddExpenseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AddExpenseViewModel::class.java]

        setupCategoryDropdown()
        setupDatePicker()
        setupAddButton()

        return binding.root
    }

    private fun setupCategoryDropdown() {
        val categories = listOf("Food", "Transport", "Rent", "Utilities", "Others")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.dropdownCategory.setAdapter(adapter)
    }

    private fun setupDatePicker() {
        binding.buttonSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val date = "$dayOfMonth/${month + 1}/$year"
                    viewModel.setDate(date)
                    binding.buttonSelectDate.text = date
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }

    private fun setupAddButton() {
        binding.buttonAddExpense.setOnClickListener {
            val amountText = binding.editTextAmount.text.toString()
            val category = binding.dropdownCategory.text.toString()
            val description = binding.editTextDescription.text.toString()
            val date = viewModel.selectedDate.value ?: ""

            if (amountText.isEmpty() || category.isEmpty() || date.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(context, "Enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.saveExpense(amount, category, description, date)

            Toast.makeText(context, "Expense Added!", Toast.LENGTH_SHORT).show()

            binding.editTextAmount.text?.clear()
            binding.dropdownCategory.text?.clear()
            binding.editTextDescription.text?.clear()
            binding.buttonSelectDate.text = "Select Date"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
