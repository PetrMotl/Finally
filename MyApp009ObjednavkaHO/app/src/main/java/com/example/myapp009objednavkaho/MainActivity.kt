package com.example.myapp009objednavkaho

import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp009objednavkaho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()


        // binding settings
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Objednávka pumpy"

        binding.btnObjednavka.setOnClickListener {

            // načtení ID vybraného radioButtonu z radioGroup
            val pumpaRbId = binding.rgPumpy.checkedRadioButtonId

            val pumpa = findViewById<RadioButton>(pumpaRbId)

            val senzor = binding.cbSenzor.isChecked
            val bluetooth = binding.cbBluetooth.isChecked
            val aipodpora = binding.cbAI.isChecked

            val objednavkaText = "Souhrn objednávky: " +
                    "${pumpa.text}" +
                    (if(senzor) "; kontinuální měření" else "") +
                    (if(bluetooth) "; bluetooth" else "") +
                    (if(aipodpora) "; léčba s AI" else "")

            binding.tvObjednavka.text = objednavkaText

        }

        // Změna obrázku v závislosti na vybraném radioButtonu

        binding.rbPumpa1.setOnClickListener {
            binding.ivPumpa.setImageResource(R.drawable.tandem)
        }

        binding.rbPumpa2.setOnClickListener {
            binding.ivPumpa.setImageResource(R.drawable.minimed)
        }

        binding.rbPumpa3.setOnClickListener {
            binding.ivPumpa.setImageResource(R.drawable.ypso)
        }

    }
}