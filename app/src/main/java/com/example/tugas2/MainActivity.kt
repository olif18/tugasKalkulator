package com.example.tugas2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val angka1 = findViewById<EditText>(R.id.angka1)
        val angka2 = findViewById<EditText>(R.id.angka2)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val operator = findViewById<RadioGroup>(R.id.operator)


        btnHitung.setOnClickListener {
            val nilai_angka1 = angka1.text.toString().toDoubleOrNull()
            val nilai_angka2 = angka2.text.toString().toDoubleOrNull()

            if (nilai_angka1 != null && nilai_angka2 != null) {
                val hasil = do_hitung_hasil(nilai_angka1, nilai_angka2, operator)

                // Mulai Activity baru dan kirim hasil perhitungan
                val intent = Intent(this, HasilActivity::class.java).apply {
                    putExtra("EXTRA_HASIL", hasil)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun do_hitung_hasil(angka1:Double, angka2 :Double, operator: RadioGroup): String{
        var hitungHasil:Double = 0.0
        val pilihOperator = when (operator.checkedRadioButtonId) {
            R.id.tambah -> "+"
            R.id.kurang -> "-"
            R.id.kali -> "*"
            R.id.bagi -> "/"
            else -> null
        }

        when(pilihOperator){
            "+" -> hitungHasil = angka1 + angka2
            "-" -> hitungHasil = angka1 - angka2
            "*" -> hitungHasil = angka1 * angka2
            "/" -> hitungHasil = angka1 / angka2
            else -> {
                Toast.makeText(this, "Silahkan pilih operasi terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        return hitungHasil.toString()
    }
}