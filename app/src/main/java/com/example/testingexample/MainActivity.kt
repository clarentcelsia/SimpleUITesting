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

    lateinit var dataSource: RemoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {

        dataSource = RemoteDataSource()
        supportFragmentManager.fragmentFactory = AppFragmentFactory(dataSource)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.fragments.size == 0){
            val data = 0
            val bundle = Bundle()
            bundle.putInt("bookId", data)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment::class.java, bundle)
                .commit()
        }

    }
}

