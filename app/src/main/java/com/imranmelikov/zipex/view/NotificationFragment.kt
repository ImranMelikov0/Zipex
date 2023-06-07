package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private lateinit var binding:FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNotificationBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(NotificationFragmentDirections.actionNotificationFragmentToHomeFragment())
        }
        return binding.root
    }
}