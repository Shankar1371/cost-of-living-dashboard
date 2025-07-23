package com.example.personalcostdashboard.ui.analytics

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.personalcostdashboard.databinding.FragmentAnalyticsBinding
import com.example.personalcostdashboard.ui.analytics.viewmodel.AnalyticsViewModel
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter




class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalyticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAnalyticsData()
    }

    private fun observeAnalyticsData() {
        viewModel.analyticsData.observe(viewLifecycleOwner) { data ->
            // Summary
            binding.textTotalSpent.text = "Spent: $${String.format("%.2f", data.totalSpent)}"
            binding.textTotalSaved.text = "Saved: $${String.format("%.2f", data.totalSaved)}"
            binding.textAvgExpense.text = "Avg/Day: $${String.format("%.2f", data.averageDaily)}"

            // Pie Chart - category-wise
            val pieEntries = data.categoryWise.map {
                PieEntry(it.value.toFloat(), it.key)
            }

            val pieDataSet = PieDataSet(pieEntries, "Spending by Category")
            pieDataSet.colors = listOf(
                Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.YELLOW
            )

            binding.piechart.data = PieData(pieDataSet)
            binding.piechart.description.isEnabled = false
            binding.piechart.invalidate()

            // Line Chart - monthly trend
            val entries = data.monthlySpend.entries.mapIndexed { index, entry ->
                Entry(index.toFloat(), entry.value.toFloat())
            }

            val lineDataSet = LineDataSet(entries, "Monthly Trend")
            lineDataSet.color = Color.BLACK
            lineDataSet.setCircleColor(Color.BLUE)
            lineDataSet.valueTextSize = 10f

            val months = data.monthlySpend.keys.toList()
            binding.linechart.data = LineData(lineDataSet)
            binding.linechart.xAxis.valueFormatter = IndexAxisValueFormatter(months)
            binding.linechart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            binding.linechart.axisRight.isEnabled = false
            binding.linechart.description.isEnabled = false
            binding.linechart.invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
