package com.example.testingexample.source

import android.util.Log
import com.example.testingexample.model.Books
import com.example.testingexample.model.Data

class RemoteDataSource : DataSource {

    private var remote = HashMap<Int, Data>(Books.books.size)

    init {
        for(book in Books.books){
            addBook(book)
            Log.i("TAG", "list: ${book.title}")
        }
    }

    private fun addBook(data: Data){
        remote[data.id] = data
    }

    override fun getBooks(): List<Data>{
        Log.i("TAG", "getBooks: ${remote.values.toString()}")
        return ArrayList(remote.values)
    }

    override fun getBook(dataID: Int): Data? {
        return remote[dataID]
    }
}

