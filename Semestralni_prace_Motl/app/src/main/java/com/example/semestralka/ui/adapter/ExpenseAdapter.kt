package com.example.semestralka.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semestralka.R
import com.example.semestralka.data.database.entities.Expense

//Prostředník, který převádí data na vizuální prvky (view), nepracuje přímo s dtb, pouze zobrazuje data, které mu aktivity předá
class ExpenseAdapter(
    private var expenses: List<Expense>, //seznam výdajů, které se budou zobrazovat
    private val onItemLongClick: (Expense) -> Unit, //nakonec navyužito
    private val onDeleteClick: (Expense) -> Unit  // Přidáno: callback pro smazání
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.expense_name)
        val priceTextView: TextView = itemView.findViewById(R.id.expense_price)
        val deleteButton: ImageButton = itemView.findViewById(R.id.btn_delete)  // Přidáno tlačítko
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder { //vytváří nový vzhled pro každý řádek v seznamu
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false) //načte item_expense.xml a převedeho ho na view
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) { //naplní vizuální prvky konrétními daty
        val expense = expenses[position] //získá výdaj podle pozice v seznamu
        holder.nameTextView.text = expense.name //nastavení názvu
        holder.priceTextView.text = "${expense.totalPrice} Kč" //nastavení ceny

        // Kliknutí na tlačítko pro smazání
        holder.deleteButton.setOnClickListener {
            onDeleteClick(expense)
        }

        // Dlouhý klik pro jinou akci (nakonec neprovádím)
        holder.itemView.setOnLongClickListener {
            onItemLongClick(expense)
            true
        }
    }

    override fun getItemCount(): Int = expenses.size //vrací počet položek, které má adaptér zobrazit

    fun updateExpenses(newExpenses: List<Expense>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }
}

