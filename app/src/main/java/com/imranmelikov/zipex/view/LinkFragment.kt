package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentLinkBinding
import com.imranmelikov.zipex.databinding.FragmentTariffBinding

class LinkFragment : Fragment() {
private lateinit var binding: FragmentLinkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLinkBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(LinkFragmentDirections.actionLinkFragmentToHomeFragment())
        }
        return binding.root
    }
}