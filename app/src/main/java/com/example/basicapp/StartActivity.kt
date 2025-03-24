package com.example.basicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.TextView

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // List of mobile software engineering challenges
        val challenges = listOf(
            "1. Battery Consumption",
            "2. Network Issues",
            "3. Security Concerns",
            "4. User Interface Design",
            "5. Performance Optimization",
            "6. App Store Approval Process"
        )

        // Find the TextView and display challenges
        val challengesTextView = findViewById<TextView>(R.id.challengesTextView)
        challengesTextView.text = challenges.joinToString("\n")

        // Find button and set click listener to go back to MainActivity
        val mainActivityButton = findViewById<Button>(R.id.mainActivityButton)
        mainActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
