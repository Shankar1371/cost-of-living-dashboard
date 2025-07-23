package com.example.personalcostdashboard.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

data class HousingPriceEntry(
    val regionName: String,
    val state: String,
    val monthlyPrices: Map<String, Float>
)

object HousingDataProcessor {

    fun loadHousingData(context: Context): List<HousingPriceEntry> {
        val entries = mutableListOf<HousingPriceEntry>()

        val inputStream = context.assets.open("housing_data.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val lines = reader.readLines()

        val headers = lines.first().split(",") // Dates start from 6th column

        for (line in lines.drop(1)) {
            val cols = line.split(",")
            if (cols.size < 7) continue

            val regionName = cols[2]
            val state = cols[4]
            val priceMap = mutableMapOf<String, Float>()

            for (i in 5 until cols.size) {
                val date = headers[i]
                val price = cols[i].toFloatOrNull()
                if (price != null) priceMap[date] = price
            }

            entries.add(
                HousingPriceEntry(
                    regionName = regionName,
                    state = state,
                    monthlyPrices = priceMap
                )
            )
        }

        return entries
    }

    fun getAveragePriceForMonth(entries: List<HousingPriceEntry>, month: String): Float {
        val prices = entries.mapNotNull { it.monthlyPrices[month] }
        return if (prices.isNotEmpty()) prices.sum() / prices.size else 0f
    }
}
