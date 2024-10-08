package com.example.myapp002homework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompast.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val etName = findViewById<EditText>(R.id.etName)
        val etPlatform = findViewById<EditText>(R.id.etPlatform)
        val etVersion = findViewById<EditText>(R.id.etVersion)
        val etNumber = findViewById<EditText>(R.id.etNumber)
        val tvInformation = findViewById<TextView>(R.id.tvInformation)
        val bntSend = findViewById<Button>(R.id.btnSend)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        // Nastavení obsluhy pro tlačítko Odeslat
        bntSend.setOnClickListener {
            val name = etName.text.toString()
            val platform = etPlatform.text.toString()
            val version = etVersion.text.toString()
            val number = etNumber.text.toString()

            // Zobrazení textu v TextView
            val formattedText =
                "Testovací běh testu $name proběhl úspěšně na platformě $platform a verzi $version. Test proběhl $number krát. "
            tvInformation.text = formattedText
        }

        // Nastavení obsluhy pro tlačítko Odeslat
        btnDelete.setOnClickListener {
            etName.text.clear()
            etPlatform.text.clear()
            etVersion.text.clear()
            etNumber.text.clear()

            tvInformation.text = ""
        }
    }
}