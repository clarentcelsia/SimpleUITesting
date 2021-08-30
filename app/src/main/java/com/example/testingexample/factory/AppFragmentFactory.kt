package com.example.testingexample.factory

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.testingexample.DetailFragment
import com.example.testingexample.ListFragment
import com.example.testingexample.MainFragment
import com.example.testingexample.source.RemoteDataSource

class AppFragmentFactory(
    val remoteDataSource: RemoteDataSource?,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (className) {
            ListFragment::class.java.name -> {
                if (remoteDataSource != null) {
                    Log.i("TAG", "instantiate: remotedatasurce not null")
                    ListFragment(remoteDataSource)
                } else super.instantiate(classLoader, className)
            }

            MainFragment::class.java.name -> {
                if (remoteDataSource != null) {
                    MainFragment(remoteDataSource)
                } else
                    super.instantiate(classLoader, className)
            }

            DetailFragment::class.java.name -> {
                DetailFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}