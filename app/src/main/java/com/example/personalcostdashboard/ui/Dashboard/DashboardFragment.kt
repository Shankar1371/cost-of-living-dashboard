package com.example.personalcostdashboard.ui.Dashboard

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalcostdashboard.PersonalCostDashboardApp
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.data.repository.ExpenseRepository
import com.example.personalcostdashboard.databinding.FragmentDashboardBinding
import com.example.personalcostdashboard.ui.Dashboard.RecentTransactionAdapter
import com.example.personalcostdashboard.ui.Settings.SettingsViewModel
import java.text.DecimalFormat

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by activityViewModels()

    private lateinit var expenseRepository: ExpenseRepository
    private lateinit var transactionAdapter: RecentTransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val app = requireActivity().application as PersonalCostDashboardApp
        expenseRepository = app.repository

        // Setup RecyclerView
        binding.recyclerViewTransactions.layoutManager = LinearLayoutManager(requireContext())
        transactionAdapter = RecentTransactionAdapter(currencySymbol = "$") // placeholder symbol
        binding.recyclerViewTransactions.adapter = transactionAdapter

        observeSettingsAndExpenses()
    }

    private fun observeSettingsAndExpenses() {
        settingsViewModel.settings.observe(viewLifecycleOwner) { settings ->
            if (settings != null) {
                val budget = settings.monthlyBudget
                val currency = settings.currencySymbol
                val formatter = DecimalFormat("#,##0.00")

                // Update symbol in adapter
                transactionAdapter = RecentTransactionAdapter(currency)
                binding.recyclerViewTransactions.adapter = transactionAdapter

                expenseRepository.getAllExpenses().observe(viewLifecycleOwner) { expenses ->
                    val totalSpent = expenses.sumOf { it.amount }
                    val remaining = budget - totalSpent
                    val percent = if (budget > 0) (totalSpent / budget * 100).toInt() else 0

                    binding.textMonthlyBudget.text = "$currency${formatter.format(budget)}"
                    binding.textMonthSpent.text = "$currency${formatter.format(totalSpent)}"
                    binding.textRemainingBudget.text = "$currency${formatter.format(remaining)}"
                    binding.textProgressPercent.text = "$percent%"

                    // 🔥 Show last 5 transactions (most recent first)
                    val recentTransactions = expenses.sortedByDescending { it.dateString }.take(5)
                    transactionAdapter.submitList(recentTransactions)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
