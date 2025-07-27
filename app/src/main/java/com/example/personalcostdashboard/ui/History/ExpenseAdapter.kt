package com.example.personalcostdashboard.ui.History

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.personalcostdashboard.data.Expense
import com.example.personalcostdashboard.databinding.ItemExpenseBinding

// Optional listener interface for edit/delete actions
interface OnExpenseClickListener {
    fun onEditClick(expense: Expense)
    fun onDeleteClick(expense: Expense)
}

class ExpenseAdapter(
    private val listener: OnExpenseClickListener? = null // Optional for Dashboard
) : ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(DiffCallback) {

    inner class ExpenseViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)

        with(holder.binding) {
            textViewTitle.text = expense.description
            textViewDate.text = expense.dateString
            textViewAmount.text = "$${"%.2f".format(expense.amount)}"

            // Show or hide buttons based on whether a listener is passed
            btnEdit.apply {
                visibility = if (listener != null) ViewGroup.VISIBLE else ViewGroup.GONE
                setOnClickListener { listener?.onEditClick(expense) }
            }

            btnDelete.apply {
                visibility = if (listener != null) ViewGroup.VISIBLE else ViewGroup.GONE
                setOnClickListener { listener?.onDeleteClick(expense) }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Expense>() {
            override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem == newItem
            }
        }
    }
}
