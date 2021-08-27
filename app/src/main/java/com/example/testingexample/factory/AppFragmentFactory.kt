package com.example.testingexample.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.testingexample.DetailFragment

class AppFragmentFactory: FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when(className){
            DetailFragment::class.java.name -> {
                DetailFragment()
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
}