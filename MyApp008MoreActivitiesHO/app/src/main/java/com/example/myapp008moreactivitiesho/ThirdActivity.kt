package com.example.myapp008moreactivitiesho

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        val twInfo2 = findViewById<TextView>(R.id.twInfo2)

        // Načtení dat z intentu
        val champname = intent.getStringExtra("CHAMP_NAME")
        twInfo2.text = "Vybraný Champ: $champname"

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

    }
}