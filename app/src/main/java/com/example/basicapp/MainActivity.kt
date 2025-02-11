package com.example.basicapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        // Button for explicit intent
        val explicitButton = findViewById<Button>(R.id.explicitButton)
        explicitButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        // Button for implicit intent (e.g., opening a web page)
        val implicitButton = findViewById<Button>(R.id.implicitButton)
        implicitButton.setOnClickListener {
                                                                                                                                                                                                                                                                0
            val intent = Intent("com.example.myandroidapp.OPEN_SECOND_ACTIVITY")
            startActivity(intent)
        }
    }
}