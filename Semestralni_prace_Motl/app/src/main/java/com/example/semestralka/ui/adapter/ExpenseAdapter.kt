package com.example.semestralka.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralka.R
import com.example.semestralka.data.database.entities.Expense

class ExpenseAdapter(
    private var expenses: List<Expense>,
    private val onItemLongClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.expense_name)
        val priceTextView: TextView = itemView.findViewById(R.id.expense_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.nameTextView.text = expense.name
        holder.priceTextView.text = "${expense.totalPrice} Kč"

        holder.itemView.setOnLongClickListener {
            onItemLongClick(expense)
            true
        }
    }

    override fun getItemCount(): Int = expenses.size

    // ✅ Přidáno: Aktualizace dat v adapteru
    fun updateExpenses(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }
}
