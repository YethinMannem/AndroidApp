package com.example.basicapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CaptureImageActivity : AppCompatActivity() {

    private lateinit var cameraOpenId: Button
    lateinit var clickImageId: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_image)

        cameraOpenId = findViewById(R.id.openCamera)
        clickImageId = findViewById(R.id.imageCaptured)

        cameraOpenId.setOnClickListener(View.OnClickListener { v: View? ->
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, pic_id)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pic_id) {
            val photo = data!!.extras!!["data"] as Bitmap?
            clickImageId.setImageBitmap(photo)
        }
    }

    companion object {
        private const val pic_id = 123
    }
}
