package com.example.personalcostdashboard.ui.Settings

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.personalcostdashboard.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()

    //  Currency symbol options
    private val currencySymbols = listOf("$", "₹", "€", "£", "¥", "₩", "₽", "₱", "₺")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Attach dropdown adapter to spinnerCurrency
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, currencySymbols)
        binding.spinnerCurrency.setAdapter(adapter)

        //  Load saved settings into UI
        viewModel.settings.observe(viewLifecycleOwner) { settings ->
            settings?.let {
                binding.editTextBudget.setText(it.monthlyBudget.toString())
                binding.spinnerCurrency.setText(it.currencySymbol, false)
                binding.switchDarkMode.isChecked = it.isDarkMode
                binding.switchNotifications.isChecked = it.notificationsEnabled
            }
        }

        binding.buttonSaveSettings.setOnClickListener {
            val budget = binding.editTextBudget.text.toString().toDoubleOrNull()
            val rawCurrency = binding.spinnerCurrency.text.toString().trim()
            val currencySymbol = extractSymbolOnly(rawCurrency)

            if (budget == null || currencySymbol.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter valid budget and currency", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedSettings = UserSettings(
                id = 1,
                monthlyBudget = budget,
                currencySymbol = currencySymbol,
                isDarkMode = binding.switchDarkMode.isChecked,
                notificationsEnabled = binding.switchNotifications.isChecked
            )

            lifecycleScope.launch {
                viewModel.saveSettings(updatedSettings)
                Toast.makeText(requireContext(), "Settings saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Ensure only symbol is saved
    private fun extractSymbolOnly(input: String): String {
        val regex = Regex("[\\p{Sc}]+") // Unicode currency symbols
        return regex.find(input)?.value ?: input.takeLast(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
