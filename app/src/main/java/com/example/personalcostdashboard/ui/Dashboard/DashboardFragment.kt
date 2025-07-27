package com.example.personalcostdashboard.ui.Dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.personalcostdashboard.databinding.FragmentDashboardBinding
import com.example.personalcostdashboard.ui.dashboard.viewmodel.DashboardViewModel
import com.example.personalcostdashboard.ui.Dashboard.DashboardViewModelFactory
import com.example.personalcostdashboard.PersonalCostDashboardApp
import com.example.personalcostdashboard.ui.History.ExpenseAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory((requireActivity().application as PersonalCostDashboardApp).repository)
    }

    private lateinit var adapter: ExpenseAdapter

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

        adapter = ExpenseAdapter()
        binding.recyclerViewTransactions.adapter = adapter

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.monthlyBudget.observe(viewLifecycleOwner) {
            binding.textMonthlyBudget.text = "$${String.format("%.2f", it)}"
        }

        viewModel.monthSpent.observe(viewLifecycleOwner) {
            binding.textMonthSpent.text = "$${String.format("%.2f", it)}"
        }

        viewModel.remainingBudget.observe(viewLifecycleOwner) {
            binding.textRemainingBudget.text = "$${String.format("%.2f", it)}"
        }

        viewModel.progressPercent.observe(viewLifecycleOwner) {
            binding.textProgressPercent.text = it
        }

        viewModel.recentExpenses.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
