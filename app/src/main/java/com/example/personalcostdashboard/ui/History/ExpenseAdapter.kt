package com.example.personalcostdashboard.ui.History

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.databinding.ItemExpenseBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseAdapter(private val expenses: List<Expense>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.binding.textViewTitle.text = expense.description
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        holder.binding.textViewDate.text = formatter.format(expense.dateString)
        holder.binding.textViewAmount.text = "$${"%.2f".format(expense.amount)}"
    }

    override fun getItemCount(): Int = expenses.size
}
