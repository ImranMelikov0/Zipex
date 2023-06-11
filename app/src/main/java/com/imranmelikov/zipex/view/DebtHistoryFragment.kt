package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import com.imranmelikov.zipex.databinding.FragmentPromoCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtHistoryFragment : Fragment() {
    private lateinit var binding: FragmentDebtHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDebtHistoryBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(DebtHistoryFragmentDirections.actionDebtHistoryFragmentToHomeFragment())
        }
        return binding.root
    }

}