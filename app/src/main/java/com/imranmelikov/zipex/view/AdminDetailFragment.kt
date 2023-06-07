package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminDetailBinding
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding

class AdminDetailFragment : Fragment() {
    private lateinit var binding: FragmentAdminDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminDetailBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminFragment())
        }
        return binding.root
    }
}