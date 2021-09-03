package com.example.tipapp

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //to bind layout and IDs after Gradle module setup
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set calculate button to function
        binding.calculate.setOnClickListener {
            calculateTip()
        }
    }

    //set algorithm
    private fun calculateTip() {
        val tipPercentage = when (binding.options.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.18
            else -> 0.15
        }

        //set return value for null or empty input before calculation to avoid app crash
        val cost = binding.costInput.text.toString().toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        var tip = tipPercentage * cost

        //check toggle button return rounded up figure
        if (binding.toggle.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    //set to display result and formatting to include currency symbol
    private fun displayTip(tip: Double) {
        binding.result.text =
            getString(R.string.tip_result, NumberFormat.getCurrencyInstance().format(tip))
    }
}