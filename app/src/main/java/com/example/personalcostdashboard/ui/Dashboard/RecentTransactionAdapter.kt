package com.example.personalcostdashboard.ui.Dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.databinding.ItemTransactionBinding
import java.text.SimpleDateFormat
import java.util.*

class RecentTransactionAdapter(
    private val currencySymbol: String
) : RecyclerView.Adapter<RecentTransactionAdapter.TransactionViewHolder>() {

    private val expenses = mutableListOf<Expense>()
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: Expense) {
            binding.textTitle.text = expense.description
            binding.textAmount.text = "$currencySymbol${String.format("%.2f", expense.amount)}"

            try {
                val date = when (expense.dateString) {
                    is Date -> expense.dateString
                    is Long -> expense.dateString
                    else -> null
                }
                binding.textDate.text = date?.let { dateFormatter.format(it) } ?: "N/A"
            } catch (e: Exception) {
                binding.textDate.text = "N/A"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size

    fun submitList(newList: List<Expense>) {
        expenses.clear()
        expenses.addAll(newList)
        notifyDataSetChanged()
    }
}