package com.example.testingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.testingexample.R
import com.example.testingexample.databinding.ActivityMainBinding
import com.example.testingexample.factory.AppFragmentFactory
import com.example.testingexample.source.RemoteDataSource
import com.example.testingexample.ui.fragment.ListFragment
import com.example.testingexample.util.Response

class MainActivity : AppCompatActivity(), Response {

    private lateinit var dataSource: RemoteDataSource

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        dataSource = RemoteDataSource()
        supportFragmentManager.fragmentFactory = AppFragmentFactory(dataSource)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment::class.java, null)
                .commit()
        }

    }

    override fun loading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = VISIBLE
        else binding.progressBar.visibility = INVISIBLE
    }
}

