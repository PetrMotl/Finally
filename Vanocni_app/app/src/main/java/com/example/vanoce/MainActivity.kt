package com.example.vanoce

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vanoce.R

class MainActivity : AppCompatActivity() { //hlavní classa aktivity, dědí od ApppCompatActivity (popora pro starší verze AN)

    override fun onCreate(savedInstanceState: Bundle?) { //metoda, která se volá při vytvoření aktivity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //načte rozhraní z activity_main

        val buttonAddWish = findViewById<Button>(R.id.button_add_wish)
        val layoutWishes = findViewById<LinearLayout>(R.id.layout_wishes)

        buttonAddWish.setOnClickListener { //Nastavení reakce na kliknutí na tlačítko
            showWishDialog { wish -> //po kliknutí se otevře dialog pro zadání přání
                addWishToLayout(wish, layoutWishes) //po potvrzení se zavolá funkce addWishToLayout, která přání přidá na obrazovku
            }
        }
    }

    private fun showWishDialog(onWishAdded: (String) -> Unit) { //private funkce, která je přístupná pouze uvnitř třídy mainactivity příjmá string a vrací unit(nic nevraci)
        val input = EditText(this) //textové pole pro zadání přání
        val dialog = AlertDialog.Builder(this) //vytvoření dialogu
            .setTitle("Napiš své přání Ježíškovi")
            .setView(input) //vloží textové pole do dialogu
            .setPositiveButton("Odeslat") { _, _ -> //po kliknutí na tlačítko odeslat se stane
                val wish = input.text.toString()  //načte se zadaný text do val wish
                if (wish.isNotBlank()) { //kontrola, zdali není textové pole prázdné, pokud není přidá se přání do layoutu
                    onWishAdded(wish)
                }
            }
            .setNegativeButton("Zrušit", null) //možnost zrušení dialogu bez zadání přání
            .create() //sestavení dialogu
        dialog.show() //zobrazení přání na obrazovce
    }

    private fun addWishToLayout(wish: String, layout: LinearLayout) { //vytvoří nový linearLayout pro přidání přání do layoutu
        val wishLayout = LinearLayout(this) //LinearLayout kontejner pro přidání přání
        wishLayout.orientation = LinearLayout.HORIZONTAL //nastavení orientace na horizontální

        val wishTextView = TextView(this) // TextView zobrazí se text přání
        wishTextView.text = wish //Nastaví se text, který obsahuje parametr wish
        wishTextView.textSize = 18f
        wishTextView.setPadding(16, 16, 16, 16) //Odsazení ze všech stran 16dp, z důvodu čitelnosti
        wishTextView.setTextColor(resources.getColor(android.R.color.white))  // Nastavení bílé barvy textu
        wishTextView.setShadowLayer(4f, 2f, 2f, android.graphics.Color.BLACK) //černý stín pod textem pro kontrast

        val deleteButton = Button(this) //deklarace tlačítka pro smazání
        deleteButton.text = "Smazat" //text v tlačítku
        deleteButton.setOnClickListener { //nastavení reakce na kliknutí na tlačítko
            layout.removeView(wishLayout) //smazání layoutu
        }

        wishLayout.addView(wishTextView) //wishTextView a deleteButton jsou přidány do wishLayout vedle sebe
        wishLayout.addView(deleteButton)

        layout.addView(wishLayout) //celý layout se přidá do layout_wishes
    }
}
