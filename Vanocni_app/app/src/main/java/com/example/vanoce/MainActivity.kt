package com.example.vanoce

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vanoce.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddWish = findViewById<Button>(R.id.button_add_wish)
        val layoutWishes = findViewById<LinearLayout>(R.id.layout_wishes)

        buttonAddWish.setOnClickListener {
            showWishDialog { wish ->
                addWishToLayout(wish, layoutWishes)
            }
        }
    }

    private fun showWishDialog(onWishAdded: (String) -> Unit) {
        val input = EditText(this)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Napiš své přání Ježíškovi")
            .setView(input)
            .setPositiveButton("Odeslat") { _, _ ->
                val wish = input.text.toString()
                if (wish.isNotBlank()) {
                    onWishAdded(wish)
                }
            }
            .setNegativeButton("Zrušit", null)
            .create()
        dialog.show()
    }

    private fun addWishToLayout(wish: String, layout: LinearLayout) {
        val wishLayout = LinearLayout(this)
        wishLayout.orientation = LinearLayout.HORIZONTAL

        val wishTextView = TextView(this)
        wishTextView.text = wish
        wishTextView.textSize = 18f
        wishTextView.setPadding(16, 16, 16, 16)
        wishTextView.setTextColor(resources.getColor(android.R.color.white))  // Nastavení bílé barvy textu
        wishTextView.setShadowLayer(4f, 2f, 2f, android.graphics.Color.BLACK)

        val deleteButton = Button(this)
        deleteButton.text = "Smazat"
        deleteButton.setOnClickListener {
            layout.removeView(wishLayout)
        }

        wishLayout.addView(wishTextView)
        wishLayout.addView(deleteButton)

        layout.addView(wishLayout)
    }
}
