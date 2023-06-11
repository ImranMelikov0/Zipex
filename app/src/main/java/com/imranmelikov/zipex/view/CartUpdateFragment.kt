package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCartUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartUpdateFragment : Fragment() {
    private lateinit var binding:FragmentCartUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartUpdateBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(CartUpdateFragmentDirections.actionCartUpdateFragmentToCartFragment())
        }
        return binding.root
    }
}