package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentOrderBinding
import com.imranmelikov.zipex.databinding.FragmentRegularOrderBinding

class RegularOrderFragment : Fragment() {
private lateinit var binding:FragmentRegularOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegularOrderBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(RegularOrderFragmentDirections.actionRegularOrderFragmentToHomeFragment())
        }
        return binding.root
    }
}