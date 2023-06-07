package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding

class AdminNewsFragment : Fragment() {
    private lateinit var binding:FragmentAdminNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentAdminNewsBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(AdminNewsFragmentDirections.actionAdminNewsFragmentToAdminFragment())
        }
        return binding.root
    }
}