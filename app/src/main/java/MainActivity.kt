package com.nazmul.sweatercalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.calculateBtn).setOnClickListener {
            calculateResults()
        }
    }

    private fun calculateResults() {
        val back = findViewById<EditText>(R.id.backPartTime).text.toString().toDoubleOrNull() ?: 0.0
        val front = findViewById<EditText>(R.id.frontPartTime).text.toString().toDoubleOrNull() ?: 0.0
        val sleeve = findViewById<EditText>(R.id.sleeveTime).text.toString().toDoubleOrNull() ?: 0.0
        val office = findViewById<EditText>(R.id.officeHours).text.toString().toDoubleOrNull() ?: 0.0
        val breakTime = findViewById<EditText>(R.id.breakTime).text.toString().toDoubleOrNull() ?: 120.0
        val machines = findViewById<EditText>(R.id.machines).text.toString().toIntOrNull() ?: 0

        val totalTime = (office * 60 - breakTime)
        val sweaterTime = back + front + (sleeve * 2)
        val totalSweaters = if (sweaterTime > 0) (totalTime * machines / sweaterTime).toInt() else 0

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("totalTime", sweaterTime.toInt())
            putExtra("totalSweaters", totalSweaters)
            putExtra("backPartCount", if (back > 0) (totalTime / back).toInt() else 0)
            putExtra("frontPartCount", if (front > 0) (totalTime / front).toInt() else 0)
            putExtra("sleeveCount", if (sleeve > 0) (totalTime / sleeve).toInt() else 0)
        }
        startActivity(intent)
    }
}
