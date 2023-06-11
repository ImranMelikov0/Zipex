package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCartBinding.inflate(inflater,container,false)
        binding.button.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment())
        }
        binding.back.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment())
        }
        return binding.root
    }
}