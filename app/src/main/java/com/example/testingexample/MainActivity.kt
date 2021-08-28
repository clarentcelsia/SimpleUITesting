package com.example.testingexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var container: ImageView
    private lateinit var btn_open: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        btn_open = findViewById(R.id.open_gallery)

        btn_open.setOnClickListener {
            open_gallery()
        }
    }

    private fun open_gallery(){
        val intent = Intent()
            .setType("image/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val data = it.data?.data
            container.visibility = VISIBLE
            Glide.with(this).load(data).into(container)
        }
    }
}

