package com.example.covacutil

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var pinCodeTextView: TextView
    private lateinit var datePickerBtn: Button
    private lateinit var searchBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        pinCodeTextView = findViewById(R.id.editTextPincode)
        datePickerBtn = findViewById(R.id.datePickerBtn)
        searchBtn = findViewById(R.id.searchBtn)

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd-MM-yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            datePickerBtn.text = sdf.format(cal.time)

        }

        datePickerBtn.setOnClickListener {
            DatePickerDialog(this@HomeActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        searchBtn.setOnClickListener {
            getResults()
        }

    }

    private fun getResults() {

        val pincode: String = pinCodeTextView.text.toString()
        val date: String = datePickerBtn.text.toString()

        if(pinCodeTextView.text.isBlank() || pinCodeTextView.text.length != 6){
            Toast.makeText(this, "Please enter a valid pincode", Toast.LENGTH_SHORT).show()
            return
        }else if(datePickerBtn.text.isBlank()){
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("pincode", pincode)
        intent.putExtra("date", date)
        startActivity(intent)
    }
}