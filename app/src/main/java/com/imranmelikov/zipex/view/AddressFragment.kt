package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAddressBinding
import com.imranmelikov.zipex.databinding.FragmentBonusBinding


class AddressFragment : Fragment() {
    private lateinit var binding:FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddressBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToHomeFragment())
        }
        val myColor= ContextCompat.getColor(requireContext(),R.color.primary)
        binding.amerikaunvan.setOnClickListener {
            binding.linearlayout1.visibility=View.VISIBLE
            binding.linearlayout2.visibility=View.GONE
            binding.turkiyeunvan.setBackgroundColor(Color.WHITE)
            binding.turkiyeunvan.setTextColor(myColor)
            binding.amerikaunvan.setTextColor(Color.WHITE)
            binding.amerikaunvan.setBackgroundColor(myColor)
        }
        binding.turkiyeunvan.setOnClickListener {
            binding.linearlayout1.visibility=View.GONE
            binding.linearlayout2.visibility=View.VISIBLE
            binding.turkiyeunvan.setBackgroundColor(myColor)
            binding.turkiyeunvan.setTextColor(Color.WHITE)
            binding.amerikaunvan.setTextColor(myColor)
            binding.amerikaunvan.setBackgroundColor(Color.WHITE)
        }
        return binding.root
    }

}