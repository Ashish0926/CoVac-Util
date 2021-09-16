package com.example.covacutil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    var sessions = mutableListOf<Session>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainAdapter(this@MainActivity, sessions)

        val recyclerView: RecyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        getData()

        val bookSlotBtn: Button = findViewById(R.id.bookSlotBtn)
        bookSlotBtn.setOnClickListener { view ->
            val cowin_url = "https://www.cowin.gov.in/home"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(cowin_url)))
        }

    }

    private fun getData() {
        val pincode = intent.getStringExtra("pincode").toString()
        val date = intent.getStringExtra("date").toString()

        val dataContainer = ApiService.dataInstance.getSessions(pincode, date)

        dataContainer.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.d("TAG", "Successful")
                val data = response.body()

                if (data != null) {
                    sessions.addAll(data.sessions)
                    if (sessions.size == 0) {
                        Toast.makeText(this@MainActivity, "Sorry! No Session available in your locality", Toast.LENGTH_SHORT).show()
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("TAG", "Something went wrong")
            }

        })
    }
}