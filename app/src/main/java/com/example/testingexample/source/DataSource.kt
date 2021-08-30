package com.example.testingexample.source

import com.example.testingexample.model.Data

interface DataSource {
    fun getBook(dataID: Int): Data?
    fun getBooks(): List<Data>
}