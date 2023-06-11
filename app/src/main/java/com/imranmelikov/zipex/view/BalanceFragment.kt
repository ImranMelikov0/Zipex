package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentBalanceBinding
import com.imranmelikov.zipex.databinding.FragmentBonusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BalanceFragment : Fragment() {
    private lateinit var binding:FragmentBalanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBalanceBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
        }
        return binding.root
    }
}