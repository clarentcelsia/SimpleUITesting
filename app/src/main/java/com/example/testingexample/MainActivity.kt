package com.example.testingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity: AppCompatActivity() {

    private lateinit var container: TextView
    private lateinit var btn_open: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        btn_open = findViewById(R.id.launch)

        btn_open.setOnClickListener {
            launch_dialog()
        }

    }

    private fun custom_toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun launch_dialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialogview, null)
        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        MaterialAlertDialogBuilder(this)
            .setView(view)
            .setPositiveButton(getString(R.string.submit)){ _, _ ->
                val name = etName.text.toString()
                container.text = name
                custom_toast(buildToastMessage(name))
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .setCancelable(false)
            .show()
    }

    companion object{
        fun buildToastMessage(name: String) = "Welcome $name"
    }
}

