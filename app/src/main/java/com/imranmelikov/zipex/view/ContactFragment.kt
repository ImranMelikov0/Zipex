package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentContactBinding
import com.imranmelikov.zipex.databinding.FragmentCourierBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentContactBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(ContactFragmentDirections.actionContactFragmentToHomeFragment())
        }
        return binding.root
    }
}