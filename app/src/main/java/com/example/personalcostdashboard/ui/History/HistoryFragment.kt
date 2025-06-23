package com.example.personalcostdashboard.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.databinding.FragmentHistoryBinding
import com.example.personalcostdashboard.ui.history.adapter.ExpenseAdapter
import com.example.personalcostdashboard.viewmodel.ExpenseViewModel

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel with AndroidViewModelFactory
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[ExpenseViewModel::class.java]

        // Setup RecyclerView
        expenseAdapter = ExpenseAdapter()
        binding.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = expenseAdapter
        }

        // Observe LiveData and update RecyclerView
        viewModel.allExpenses.observe(viewLifecycleOwner) { expenses: List<Expense> ->
            expenseAdapter.submitList(expenses.reversed()) // Latest first
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
