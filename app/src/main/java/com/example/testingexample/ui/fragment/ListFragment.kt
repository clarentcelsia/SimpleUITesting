package com.example.testingexample.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingexample.R
import com.example.testingexample.adapter.ListAdapter
import com.example.testingexample.databinding.FragmentListBinding
import com.example.testingexample.source.RemoteDataSource
import com.example.testingexample.util.Constant.Companion.NETWORK_DELAY
import com.example.testingexample.util.EspressoIdlingResource
import com.example.testingexample.util.Response
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListFragment(
    val dataSource: RemoteDataSource
) : Fragment(){

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private lateinit var listAdapter: ListAdapter
    private lateinit var response: Response

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachAdapterToRecyclerView()
        getData()
    }

    private fun attachAdapterToRecyclerView(){
        listAdapter = ListAdapter()
        binding.list.apply {
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
    }

    private fun getData(){
        EspressoIdlingResource.increment()
        response.loading(true)
        val job = GlobalScope.launch(IO) {
            delay(NETWORK_DELAY)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Main) {
                response.loading(false)
                listAdapter.submitList(dataSource.getBooks())
                EspressoIdlingResource.decrement()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            response = requireContext() as Response
        }catch (e: ClassCastException){
            Log.e("TAG", "onAttach: Implement interface in Activity: ${e.message}", )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}