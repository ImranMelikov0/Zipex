package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentTrendyolConfirmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendyolConfirmFragment : Fragment() {
    private lateinit var binding:FragmentTrendyolConfirmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTrendyolConfirmBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(TrendyolConfirmFragmentDirections.actionTrendyolConfirmFragmentToHomeFragment())
        }
        return binding.root
    }
}