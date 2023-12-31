package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentConfirmationBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentConfirmationBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToHomeFragment())
        }
        return binding.root
    }
}