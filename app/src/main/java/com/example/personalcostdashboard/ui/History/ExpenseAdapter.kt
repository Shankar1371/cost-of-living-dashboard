package com.example.personalcostdashboard.ui.History

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.databinding.ItemExpenseBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Interface for handling clicks
interface OnExpenseClickListener {
    fun onEditClick(expense: Expense)
    fun onDeleteClick(expense: Expense)
}

class ExpenseAdapter(
    private var expenses: List<Expense>,
    private val listener: OnExpenseClickListener
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]

        holder.binding.textViewDate.text=expense.dateString;

        with(holder.binding) {
            textViewTitle.text = expense.description
            textViewDate.text = expense.dateString
            textViewAmount.text = "$${"%.2f".format(expense.amount)}"

            // Click listeners for Edit and Delete
            btnEdit.setOnClickListener { listener.onEditClick(expense) }
            btnDelete.setOnClickListener { listener.onDeleteClick(expense) }
        }
    }
    fun updateExpenses(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = expenses.size
}
