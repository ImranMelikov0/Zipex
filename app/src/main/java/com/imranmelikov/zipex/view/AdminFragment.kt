package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminFragment : Fragment() {
    private lateinit var binding: FragmentAdminBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToHomeFragment())
        }
        binding.buttonadmin.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminDetailFragment())
        }
        binding.buttonadminnews.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminNewsFragment())
        }
        binding.buttonadminnotification.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminNotificationFragment2())
        }
        return binding.root
    }
}