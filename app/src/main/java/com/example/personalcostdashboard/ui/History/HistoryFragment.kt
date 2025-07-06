package com.example.personalcostdashboard.ui.History

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalcostdashboard.databinding.FragmentHistoryBinding
import com.example.personalcostdashboard.ui.common.ExpenseAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeExpenses()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = ExpenseAdapter()
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewHistory.adapter = adapter
    }

    private fun observeExpenses() {
        viewModel.expenses.observe(viewLifecycleOwner) { expenses ->
            adapter.submitList(expenses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
