package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCartUpdateBinding
import com.imranmelikov.zipex.databinding.FragmentLinkDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LinkDetailFragment : Fragment() {
    private lateinit var binding: FragmentLinkDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLinkDetailBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(LinkDetailFragmentDirections.actionLinkDetailFragmentToOrderFragment())
        }
        return binding.root
    }

}