package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentPasswordBinding
import com.imranmelikov.zipex.databinding.FragmentPromoCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : Fragment() {
    private lateinit var binding: FragmentPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPasswordBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(PasswordFragmentDirections.actionPasswordFragmentToHomeFragment())
        }
        return binding.root
    }
}