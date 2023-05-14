package com.vitaly.livedatacounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.vitaly.livedatacounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.seconds().observe(this) {
            binding.textView.text = it.toString()
        }

        viewModel.finished().observe(this) {
            if (it) Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
        }

        binding.btnStart.setOnClickListener {
            if (binding.editTextNumber.text.isEmpty() || binding.editTextNumber.text.length < 4)
                Toast.makeText(this, "Invalid number", Toast.LENGTH_LONG).show()
            else viewModel.startTimer(binding.editTextNumber.text.toString().toLong())
        }

        binding.btnStop.setOnClickListener {
            viewModel.stopTimer()
        }

    }
}