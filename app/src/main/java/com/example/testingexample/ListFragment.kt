package com.example.testingexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testingexample.adapter.ListAdapter
import com.example.testingexample.model.Data
import com.example.testingexample.source.RemoteDataSource

class ListFragment(
    val dataSource: RemoteDataSource
) : Fragment(R.layout.fragment_list) {

    private lateinit var list: RecyclerView
    private lateinit var listAdapter: ListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = view.findViewById(R.id.list)
        attachRecyclerView()
    }

    private fun attachRecyclerView(){
        listAdapter = ListAdapter()
        list.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            setHasFixedSize(true)
            adapter = listAdapter
        }

        listAdapter.onItemSelected { data->
            val bundle = Bundle()
            bundle.putParcelable("data", data)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DetailFragment::class.java, bundle)
                ?.addToBackStack("DetailFragment")
                ?.commit()
        }

        listAdapter.listDiffer.submitList(dataSource.getBooks())
    }

}