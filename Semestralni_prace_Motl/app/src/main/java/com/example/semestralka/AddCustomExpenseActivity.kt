package com.example.semestralka

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.semestralka.data.database.DatabaseBuilder
import com.example.semestralka.data.database.entities.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class AddCustomExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_custom_expense)

        val descriptionEditText: EditText = findViewById(R.id.et_description)
        val quantityEditText: EditText = findViewById(R.id.et_quantity)
        val priceEditText: EditText = findViewById(R.id.et_price)
        val addButton: Button = findViewById(R.id.btn_add_custom_expense)

        // ✅ Akce pro tlačítko Přidat Výdaj
        addButton.setOnClickListener {
            val description = descriptionEditText.text.toString()
            val quantity = quantityEditText.text.toString().toIntOrNull() ?: 1
            val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (description.isNotBlank() && price > 0.0) {
                addCustomExpense(description, quantity, price)
            } else {
                Toast.makeText(this, "Zadejte všechny údaje!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ✅ Funkce pro přidání výdaje
    private fun addCustomExpense(description: String, quantity: Int, price: Double) {
        val expense = Expense(
            name = description,
            amount = price,
            quantity = quantity,
            totalPrice = price * quantity,
            description = "Manuální výběr",
            date = Date()
        )

        lifecycleScope.launch(Dispatchers.IO) {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().insertExpense(expense)
        }

        Toast.makeText(this, "$description byl přidán!", Toast.LENGTH_SHORT).show()

        // Po přidání výdaje se vrátíme na hlavní stránku
        finish()
    }
}