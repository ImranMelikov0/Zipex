package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentDebtBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding

class DebtFragment : Fragment() {
    private lateinit var binding: FragmentDebtBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDebtBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToHomeFragment())
        }
        return binding.root
    }
}