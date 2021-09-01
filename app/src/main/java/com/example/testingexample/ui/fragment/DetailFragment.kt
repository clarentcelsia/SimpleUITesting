package com.example.testingexample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.testingexample.R
import com.example.testingexample.databinding.FragmentDetailBinding
import com.example.testingexample.model.Data

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    private lateinit var datas: Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { args ->
            args.getParcelable<Data>("data").let { data ->
                data?.let { datas = data }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailTitle.text = datas.title
        binding.detailDescription.text = datas.desc
        binding.author.text = stringBuilderForDetailFragment(datas.author)
        Glide.with(this.requireContext()).load(datas.image)
            .into(binding.detailImageview.also { it.tag = datas.image })
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}