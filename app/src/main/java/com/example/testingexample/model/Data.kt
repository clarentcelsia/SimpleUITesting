package com.example.testingexample.model

import androidx.annotation.DrawableRes

data class Data(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int,
    val author: ArrayList<String>,
    val desc: String
)
