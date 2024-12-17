package com.example.week5task

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week5task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var countries = arrayOf(
        "Nepal" , "Africa" ,"England","India","China","America","Spain"
    )

    var cities = arrayOf(
        "Kathmandu" ,"Dhangadi" ,"Nepalgung", "Palpa", "kanchanpur","lalitpur"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var countryAdapter  = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            countries
        )

        var cityadapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            cities
        )
        binding.counrtySpinner.adapter = countryAdapter
        binding.citiyView.setAdapter(cityadapter)

        binding.subBut.setOnClickListener {
            val fullName = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val selectedCountry = binding.counrtySpinner.selectedItem?.toString()
            val selectedCity = binding.citiyView.text.toString()

            if (fullName.isEmpty()) {
                binding.name.error = "Enter your full name"
            } else if (email.isEmpty()) {
                binding.name.error = "Please enter email"
            } else if (password.isEmpty()) {
                binding.password.error = "Please enter password"
            } else if (selectedCountry.isNullOrEmpty() || selectedCountry == "Select Country") {
                Toast.makeText(
                    this@MainActivity,
                    "Please select a valid country",
                    Toast.LENGTH_LONG
                ).show()
            } else if (selectedCity.isEmpty() || !cities.contains(selectedCity)) {
                Toast.makeText(
                    this@MainActivity,
                    "Please select a valid city",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!binding.checkBox.isChecked) {
                Toast.makeText(
                    this@MainActivity,
                    "Please agree to the terms and conditions",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this@MainActivity, DestinationActivity::class.java)
                    intent.putExtra("name", fullName)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    intent.putExtra("gender", binding.RadioGrp.checkedRadioButtonId)
                    intent.putExtra("country", selectedCountry)
                    intent.putExtra("city", selectedCity)
                startActivity(intent)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}