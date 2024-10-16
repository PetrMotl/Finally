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

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val twInfo = findViewById<TextView>(R.id.twInfo)
        val etchamp = findViewById<EditText>(R.id.etChamp)
        val thirdButton = findViewById<Button>(R.id.btnThird)

        // Načtení dat z intentu
        val nickname = intent.getStringExtra("NICK_NAME")
        twInfo.text = "Data z první aktivity. Jméno: $nickname"


        thirdButton.setOnClickListener {
            val champname = etchamp.text.toString() // získáme text z edit text pole
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("CHAMP_NAME", champname)
            startActivity(intent)
        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }



    }
}