package com.example.personalcostdashboard.ui.inflation

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.personalcostdashboard.databinding.FragmentInflationBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class InflationFragment : Fragment() {

    private var _binding: FragmentInflationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InflationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInflationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gasPrice.observe(viewLifecycleOwner) {
            binding.textGasPrice.text = it
        }

        viewModel.electricityRate.observe(viewLifecycleOwner) {
            binding.textElectricity.text = it
        }

        viewModel.groceryIndex.observe(viewLifecycleOwner) {
            binding.textGroceryIndex.text = it
        }

        viewModel.avgRent.observe(viewLifecycleOwner) {
            binding.textRent.text = it
        }

        viewModel.rentTrendLineData.observe(viewLifecycleOwner) { lineData ->
            val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
            binding.lineChart.apply {
                data = lineData
                xAxis.valueFormatter = IndexAxisValueFormatter(months)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                axisRight.isEnabled = false
                description.isEnabled = false
                animateX(800)
                invalidate()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
