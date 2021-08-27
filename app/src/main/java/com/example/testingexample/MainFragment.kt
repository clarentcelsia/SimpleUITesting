package com.example.testingexample

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testingexample.adapter.BookAdapter
import com.example.testingexample.model.Data
import com.example.testingexample.source.RemoteDataSource

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var data: Data

    private lateinit var title: TextView

    private lateinit var image: ImageView

    private lateinit var description: TextView

    private lateinit var author: TextView

    /**
     *  RemoteDataSource should be injected with a DI framework
     *  or be passed as a constructor param to the Fragment (if using FragmentFactory)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("bookID").let { id ->
                RemoteDataSource.getBook(id).let {
                    data = it
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.main_titles)
        image = view.findViewById(R.id.main_imageview)
        description = view.findViewById(R.id.main_description)
        author = view.findViewById(R.id.main_author)

        title.text = data.title
        Glide.with(this.requireContext()).load(data.image).into(image)
        description.text = data.desc

        val bundle = Bundle()
        bundle.putStringArrayList("author", data.author)

        author.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DetailFragment::class.java, bundle)
                ?.addToBackStack("DetailFragment")
                ?.commit()
        }
    }

}
