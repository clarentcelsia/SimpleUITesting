package com.example.testingexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testingexample.R
import com.example.testingexample.model.Data
import com.example.testingexample.util.EspressoIdlingResource

class  ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private val listCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val listDiffer = AsyncListDiffer(this, listCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listIndex = listDiffer.currentList[position]
        val title = holder.view.findViewById<TextView>(R.id.titles)
        val image = holder.view.findViewById<ImageView>(R.id.imageview)
        val desc = holder.view.findViewById<TextView>(R.id.description)

        holder.view.apply {
            title.text = listIndex.title
            image.setImageResource(listIndex.image)
            desc.text = listIndex.desc
            setOnClickListener {
                selectedData?.let {
                    it(listIndex)
                }
            }
        }
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    fun submitList(list: List<Data>){
        EspressoIdlingResource.increment()
        val dataCommitCallback = Runnable {
            EspressoIdlingResource.decrement()
        }
        listDiffer.submitList(list, dataCommitCallback)
    }

    private var selectedData: ( (Data) -> Unit )? = null
    fun onItemSelected(data: ((Data) -> Unit)) {
        selectedData = data
    }
}