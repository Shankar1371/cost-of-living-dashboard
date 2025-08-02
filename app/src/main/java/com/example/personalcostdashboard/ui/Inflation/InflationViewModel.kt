package com.example.personalcostdashboard.ui.inflation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.*
import android.graphics.Color

class InflationViewModel : ViewModel() {

    private val _gasPrice = MutableLiveData<String>("$3.89/gal")
    val gasPrice: LiveData<String> = _gasPrice

    private val _electricityRate = MutableLiveData<String>("$0.16/kWh")
    val electricityRate: LiveData<String> = _electricityRate

    private val _groceryIndex = MutableLiveData<String>("↑7.4%")
    val groceryIndex: LiveData<String> = _groceryIndex

    private val _avgRent = MutableLiveData<String>("$1,180/month")
    val avgRent: LiveData<String> = _avgRent

    private val _rentTrendLineData = MutableLiveData<LineData>().apply {
        // Provide a mock data set for the rent trend chart
        value = generateMockRentTrend()
    }
    val rentTrendLineData: LiveData<LineData> = _rentTrendLineData

    private fun generateMockRentTrend(): LineData {
        // Create sample data points for six months of rent prices
        val entries = listOf(
            Entry(0f, 1150f),
            Entry(1f, 1175f),
            Entry(2f, 1190f),
            Entry(3f, 1205f),
            Entry(4f, 1220f),
            Entry(5f, 1235f)
        )
        val dataSet = LineDataSet(entries, "Rent Trend (6 months)").apply {
            color = Color.BLUE
            setCircleColor(Color.RED)
            valueTextSize = 10f
            lineWidth = 2f
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        // Wrap the dataset in LineData for charting
        return LineData(dataSet)
    }
}
