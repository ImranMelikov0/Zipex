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
import com.imranmelikov.zipex.databinding.FragmentServiceBinding
import com.imranmelikov.zipex.databinding.FragmentTariffBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TariffFragment : Fragment() {
private lateinit var binding: FragmentTariffBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTariffBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(TariffFragmentDirections.actionTariffFragmentToHomeFragment())
        }
        val myColor=ContextCompat.getColor(requireContext(),R.color.primary)
        binding.tariffTurkiye.setOnClickListener {
            binding.linearlayout1.visibility=View.VISIBLE
            binding.linearlayout2.visibility=View.GONE
            binding.tariffAmerika.setBackgroundColor(Color.WHITE)
            binding.tariffAmerika.setTextColor(myColor)
            binding.tariffTurkiye.setTextColor(Color.WHITE)
            binding.tariffTurkiye.setBackgroundColor(myColor)
        }
        binding.tariffAmerika.setOnClickListener {
            binding.linearlayout1.visibility=View.GONE
            binding.linearlayout2.visibility=View.VISIBLE
            binding.tariffAmerika.setBackgroundColor(myColor)
            binding.tariffAmerika.setTextColor(Color.WHITE)
            binding.tariffTurkiye.setTextColor(myColor)
            binding.tariffTurkiye.setBackgroundColor(Color.WHITE)
        }
        return binding.root
    }

}