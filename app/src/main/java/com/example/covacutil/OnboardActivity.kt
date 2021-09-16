package com.example.covacutil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class OnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@OnboardActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}