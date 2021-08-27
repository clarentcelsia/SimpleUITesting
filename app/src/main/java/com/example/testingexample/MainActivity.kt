package com.example.testingexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import com.example.testingexample.factory.AppFragmentFactory
import com.example.testingexample.model.Books
import com.example.testingexample.model.Data
import com.example.testingexample.source.RemoteDataSource
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = AppFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.fragments.size == 0){
            val data = 1
            val bundle = Bundle()
            bundle.putInt("bookID", data)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, bundle)
                .commit()
        }

    }
}

