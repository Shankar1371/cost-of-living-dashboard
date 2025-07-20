package com.example.personalcostdashboard.ui.inflation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.personalcostdashboard.databinding.FragmentInflationBinding
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.XAxis

import android.graphics.Color


class InflationFragment : Fragment() {

    private var _binding: FragmentInflationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInflationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Step 2: Set mock data
        val entries = listOf(
            Entry(0f, 1200f),  // Jan
            Entry(1f, 1220f),  // Feb
            Entry(2f, 1230f),  // Mar
            Entry(3f, 1250f),  // Apr
            Entry(4f, 1270f),  // May
            Entry(5f, 1280f),  // Jun
        )

        val dataSet = LineDataSet(entries, "Rent Trend")
        dataSet.color = Color.BLUE
        dataSet.setCircleColor(Color.RED)
        dataSet.valueTextSize = 12f

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        binding.lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(months)
        binding.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.description.isEnabled = false

        binding.lineChart.invalidate() // refresh chart

        // We will set chart data in Step 3
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
