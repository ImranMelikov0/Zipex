package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCartUpdateBinding
import com.imranmelikov.zipex.databinding.FragmentNewsDetailBinding
import com.imranmelikov.zipex.mvvm.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment(private var glide:RequestManager) : Fragment() {
    private lateinit var binding: FragmentNewsDetailBinding
    private lateinit var viewModel:NewsViewModel
    private var newsDetailId=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNewsDetailBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(NewsDetailFragmentDirections.actionNewsDetailFragmentToHomeFragment())
        }
        arguments?.let {
            newsDetailId=NewsDetailFragmentArgs.fromBundle(it).newsid
        }
        viewModel.getNewsSingle(newsDetailId)
        observeNews()
        return binding.root
    }
    private fun observeNews(){
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            binding.newsTitletext.text=it.title
            binding.newsText.text=it.post
            glide.load(it.imageUrl)
                .into(binding.regularImage)
        })
    }

}