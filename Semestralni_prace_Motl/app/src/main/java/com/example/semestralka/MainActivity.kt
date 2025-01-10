package com.example.semestralka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.semestralka.data.database.DatabaseBuilder
import com.example.semestralka.data.database.entities.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val beerButton: Button = findViewById(R.id.btn_add_beer)
        val clearAllButton: Button = findViewById(R.id.btn_clear_all)
        val openListButton: Button = findViewById(R.id.btn_open_expense_list)  // ✅ Tlačítko pro otevření seznamu
        val totalSpentTextView: TextView = findViewById(R.id.tv_total_spent)
        val rumButton: Button = findViewById(R.id.btn_add_rum)
        val kofolaButton: Button = findViewById(R.id.btn_add_kofola)
        val otherButton: Button = findViewById(R.id.btn_add_other)  // ✅ Tlačítko pro "Jiné"


        // ✅ Tlačítko pro přidání "Piva"
        beerButton.setOnClickListener {
            addBeerExpense()
        }

        // Tlačítko pro přidání Rumíka
        rumButton.setOnClickListener {
            addRumExpense()
        }

        //Tlačítko pro přidání Kofoly
        kofolaButton.setOnClickListener {
            addKofolaExpense()
        }


        // ✅ Tlačítko pro smazání všech výdajů
        clearAllButton.setOnClickListener {
            showClearAllDialog()
        }

        // ✅ Tlačítko pro otevření seznamu výdajů
        openListButton.setOnClickListener {
            val intent = Intent(this, ExpenseListActivity::class.java)
            startActivity(intent)
        }
        //Tlačítko pro otevření ostatní
        otherButton.setOnClickListener {
            val intent = Intent(this, AddCustomExpenseActivity::class.java)  // Otevření nové aktivity
            startActivity(intent)
        }

        // ✅ Načtení a zobrazení celkové útraty
        lifecycleScope.launch {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().getTotalSpent()
                .collectLatest { totalSpent ->
                    totalSpentTextView.text = "Celkem utraceno: ${totalSpent ?: 0.0} Kč"
                }
        }

    }

    // ✅ Přidání výdaje "Pivo"
    private fun addBeerExpense() {
        val expense = Expense(
            name = "Pivo",
            amount = 35.0,
            quantity = 1,
            totalPrice = 35.0,
            description = "Rychlá volba",
            date = Date()
        )

        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().insertExpense(expense)
        }

        Toast.makeText(this, "Pivo bylo přidáno!", Toast.LENGTH_SHORT).show()
    }

    //Přidání výdaje "Rum s kolou"
    private fun addRumExpense() {
        val expense = Expense(
            name = "Rum s kolou",
            amount = 80.0,
            quantity = 1,
            totalPrice = 80.0,
            description = "Rychlá volba",
            date = Date()
        )

        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().insertExpense(expense)
        }

        Toast.makeText(this, "Rum s kolou byl přidán!", Toast.LENGTH_SHORT).show()
    }

    //Přidání výdaje Kofola
    private fun addKofolaExpense() {
        val expense = Expense(
            name = "Kofola",
            amount = 45.0,
            quantity = 1,
            totalPrice = 45.0,
            description = "Rychlá volba",
            date = Date()
        )

        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().insertExpense(expense)
        }

        Toast.makeText(this, "Kofola byla přidána!", Toast.LENGTH_SHORT).show()
    }

    // ✅ Dialog pro potvrzení smazání všech výdajů
    private fun showClearAllDialog() {
        AlertDialog.Builder(this)
            .setTitle("Smazat všechny výdaje")
            .setMessage("Opravdu chceš smazat všechny záznamy?")
            .setPositiveButton("Ano") { _, _ ->
                clearAllExpenses()
            }
            .setNegativeButton("Ne", null)
            .show()
    }

    // ✅ Smazání všech výdajů
    private fun clearAllExpenses() {
        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().deleteAllExpenses()
        }
        Toast.makeText(this, "Všechny výdaje byly smazány.", Toast.LENGTH_SHORT).show()
    }
}
