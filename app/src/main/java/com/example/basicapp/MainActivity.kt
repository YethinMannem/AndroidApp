package com.example.basicapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val CUSTOM_PERMISSION = "com.example.basicapp.MSE412"
    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Request custom permission at runtime
        checkAndRequestCustomPermission()

        val explicitButton = findViewById<Button>(R.id.explicitButton)
        explicitButton.setOnClickListener {
            if (isPermissionGranted()) {
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Permission denied! Cannot open challenges.", Toast.LENGTH_SHORT).show()
            }
        }

        val implicitButton = findViewById<Button>(R.id.implicitButton)
        implicitButton.setOnClickListener {
            if (isPermissionGranted()) {
                val impIntent = Intent("com.example.basicapp.OPEN_SECOND_ACTIVITY")
                startActivity(impIntent)
            } else {
                Toast.makeText(this, "Permission denied! Cannot open challenges.", Toast.LENGTH_SHORT).show()
            }
        }

        val viewImageButton = findViewById<Button>(R.id.viewImageActivityButton)
        viewImageButton.setOnClickListener {
            val intent = Intent(this, CaptureImageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkAndRequestCustomPermission() {
        if (!isPermissionGranted()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, CUSTOM_PERMISSION)) {
                showPermissionExplanationDialog()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(CUSTOM_PERMISSION), REQUEST_CODE)
            }
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, CUSTOM_PERMISSION) == PackageManager.PERMISSION_GRANTED
    }

    private fun showPermissionExplanationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage("This permission is needed to access mobile software engineering challenges.")
            .setPositiveButton("OK") { _, _ ->
                ActivityCompat.requestPermissions(this, arrayOf(CUSTOM_PERMISSION), REQUEST_CODE)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "MSE412 Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Denied. Cannot access challenges.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
