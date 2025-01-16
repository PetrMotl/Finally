package com.example.semestralka

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.example.semestralka.data.database.DatabaseBuilder
import com.example.semestralka.ui.adapter.ExpenseAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//Načítání a mazání seznamu výdajů, pro vizuální přehled předává ExpenseAdapteru
class ExpenseListActivity : AppCompatActivity() {

    private lateinit var expenseAdapter: ExpenseAdapter //propojuje data z dtb s vizuálem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list) //nastavení zobrazení obrazovky

        val backButton: Button = findViewById(R.id.btn_back)
        val recyclerView: RecyclerView = findViewById(R.id.expensesRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this) //nastavení vertikálního seznamu

        // Inicializace adapteru s funkcí pro mazání
        expenseAdapter = ExpenseAdapter(emptyList(),
            onItemLongClick = { expense -> //akce pro dlouhé podržení (nevyužívám zatím)
            },
            onDeleteClick = { expense -> //akce pro kliknutí na mazání --> dialog
                // Zobrazení dialogu pro potvrzení smazání
                AlertDialog.Builder(this)
                    .setTitle("Smazat položku")
                    .setMessage("Opravdu chcete smazat položku \"${expense.name}\"?")
                    .setPositiveButton("Ano") { _, _ ->
                        lifecycleScope.launch {
                            DatabaseBuilder.getInstance(applicationContext)
                                .expenseDao()
                                .deleteExpense(expense)  // Mazání položky z databáze
                        }
                    }
                    .setNegativeButton("Ne", null)
                    .show()
            }
        )
        recyclerView.adapter = expenseAdapter //RecyclerView je propojen s adaptérem, takže se výdaje zobrazí v seznamu

        // Načtení dat z databáze
        lifecycleScope.launch {
            DatabaseBuilder.getInstance(applicationContext).expenseDao().getAllExpenses()
                .collectLatest { expenses -> //sledování změn a autamatická aktualizace zobrazení
                    expenseAdapter.updateExpenses(expenses)
                }
        }

        // Návrat zpět na hlavní stránku
        backButton.setOnClickListener {
            finish()  // Ukončí aktuální aktivitu a vrátí se na hlavní
        }
    }
}