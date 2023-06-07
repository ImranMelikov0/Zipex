package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCourierBinding
import com.imranmelikov.zipex.databinding.FragmentLinkBinding

class CourierFragment : Fragment() {
    private lateinit var binding: FragmentCourierBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCourierBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(CourierFragmentDirections.actionCourierFragmentToHomeFragment())
        }
        binding.courierfabbutton.setOnClickListener {
            findNavController().navigate(CourierFragmentDirections.actionCourierFragmentToCourierDetailFragment())
        }
        return binding.root
    }
}