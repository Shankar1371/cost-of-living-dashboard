package com.example.personalcostdashboard.ui.settings

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.personalcostdashboard.databinding.FragmentSettingsBinding
import com.example.personalcostdashboard.ui.Settings.UserSettings
import com.example.personalcostdashboard.ui.Settings.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe existing settings to prefill values
        viewModel.settings.observe(viewLifecycleOwner) { settings ->
            settings?.let {
                binding.editTextBudget.setText(it.monthlyBudget.toString())
                binding.spinnerCurrency.setText(it.currencySymbol)
                binding.switchDarkMode.isChecked = it.isDarkMode
                binding.switchNotifications.isChecked = it.notificationsEnabled
            }
        }

        binding.buttonSaveSettings.setOnClickListener {
            val budget = binding.editTextBudget.text.toString().toDoubleOrNull()
            val currency = binding.spinnerCurrency.text.toString()

            if (budget == null || currency.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter valid values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedSettings = UserSettings(
                id = 1, // Always the same ID
                monthlyBudget = budget,
                currencySymbol = currency,
                isDarkMode = binding.switchDarkMode.isChecked,
                notificationsEnabled = binding.switchNotifications.isChecked
            )

            lifecycleScope.launch {
                viewModel.saveSettings(updatedSettings)
                Toast.makeText(requireContext(), "Settings saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
