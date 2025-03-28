package com.example.sweatercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val etBodyLength = findViewById<EditText>(R.id.etBodyLength)
        val etSleeveLength = findViewById<EditText>(R.id.etSleeveLength)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {
            try {
                // ইনপুট চেক করুন
                if (TextUtils.isEmpty(etBodyLength.text) || TextUtils.isEmpty(etSleeveLength.text)) {
                    Toast.makeText(this, "ফাঁকা ফিল্ড আছে!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val bodyLength = etBodyLength.text.toString().toDouble()
                val sleeveLength = etSleeveLength.text.toString().toDouble()

                // ০ বা নেগেটিভ ইনপুট চেক
                if (bodyLength <= 0 || sleeveLength <= 0) {
                    Toast.makeText(this, "ইনপুট ০ বা নেগেটিভ হতে পারবে না!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // ক্যালকুলেশন
                val bodyYarn = (bodyLength * 3.5) / 50
                val sleeveYarn = (sleeveLength * 3.5) / 50
                val totalYarn = bodyYarn + sleeveYarn

                // রেজাল্ট ফরম্যাট করুন (দশমিকের পর ২ ঘর)
                tvResult.text = String.format("%.2f গ্রাম", totalYarn)
            } catch (e: Exception) {
                Toast.makeText(this, "একটি ত্রুটি ঘটেছে: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
