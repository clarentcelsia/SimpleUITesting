package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.testingexample.model.Data

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var datas: Data
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            args.getParcelable<Data>("data").let { data ->
                data?.let { datas = data }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.detail_title)
        author = view.findViewById(R.id.author)
        title.text = datas.title
        author.text = stringBuilderForDetailFragment(datas.author)
    }

    companion object {
        fun stringBuilderForDetailFragment(strings: ArrayList<String>?): String {
            val stringBuilder = StringBuilder()
            strings?.forEach {
                stringBuilder.append(it + "\n")
            }
            return stringBuilder.toString()
        }
    }
}