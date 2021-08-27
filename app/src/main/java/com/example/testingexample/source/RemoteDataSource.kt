package com.example.testingexample.source

import com.example.testingexample.model.Books.GOOD_OMENS
import com.example.testingexample.model.Books.SHERLOCK_HOLMES
import com.example.testingexample.model.Data

object RemoteDataSource : DataSource {

    private val remote = LinkedHashMap<Int, Data>(2)

    init {
        addBook(SHERLOCK_HOLMES)
        addBook(GOOD_OMENS)
    }

    fun addBook(data: Data){
        remote[data.id!!] = data
    }

    override fun getBook(dataID: Int): Data {
        return remote[dataID]!!
    }
}

interface DataSource {
    fun getBook(dataID: Int ): Data
}