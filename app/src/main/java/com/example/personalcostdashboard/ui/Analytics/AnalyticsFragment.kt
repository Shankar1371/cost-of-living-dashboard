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

            // LiveData values displayed directly
            binding.textTotalSpent.text = "Spent: $${String.format("%.2f", data.totalSpent)}"
            binding.textTotalSaved.text = "Saved: $${String.format("%.2f", data.totalSaved)}"
            binding.textAvgExpense.text = "Avg/Day: $${String.format("%.2f", data.averageDaily)}"

            // LiveData -> PieChart
            val pieDataSet = PieDataSet(
                data.categoryWise.map { PieEntry(it.value.toFloat(), it.key) },
                "Spending by Category"
            ).apply {
                colors = listOf(
                    Color.parseColor("#1976D2"),
                    Color.parseColor("#D32F2F"),
                    Color.parseColor("#388E3C"),
                    Color.parseColor("#FBC02D"),
                    Color.parseColor("#7B1FA2"),
                    Color.parseColor("#0288D1")
                )
                valueTextSize = 12f
                sliceSpace = 2f
            }

            binding.piechart.apply {
                this.data = PieData(pieDataSet)
                setUsePercentValues(false)
                description.isEnabled = false
                isDrawHoleEnabled = true
                animateY(800)
                invalidate()
            }

            // LiveData -> LineChart
            val lineDataSet = LineDataSet(
                data.monthlySpend.entries.mapIndexed { index, entry ->
                    Entry(index.toFloat(), entry.value.toFloat())
                },
                "Monthly Trend"
            ).apply {
                color = Color.BLACK
                setCircleColor(Color.BLUE)
                valueTextSize = 10f
                lineWidth = 2f
                mode = LineDataSet.Mode.CUBIC_BEZIER
            }

            binding.linechart.apply {
                this.data = LineData(lineDataSet)
                xAxis.valueFormatter = IndexAxisValueFormatter(data.monthlySpend.keys.toList())
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                axisRight.isEnabled = false
                description.isEnabled = false
                animateX(600)
                invalidate()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
