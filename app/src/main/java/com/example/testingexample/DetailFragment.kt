package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var author: TextView
    private val strings : ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            args.getStringArrayList("author").let { string_list ->
                string_list?.let { strings.addAll(it) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        author = view.findViewById(R.id.author)
        author.text = stringBuilderForDetailFragment(strings)
    }

    companion object {
        fun stringBuilderForDetailFragment(strings: ArrayList<String>?): String {
            val stringBuilder = StringBuilder()
            strings?.forEach {
                stringBuilder.append(it, "\n")
            }
            return stringBuilder.toString()
        }
    }
}