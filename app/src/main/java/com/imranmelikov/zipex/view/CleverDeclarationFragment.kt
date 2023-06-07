package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentCleverDeclarationBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding

class CleverDeclarationFragment : Fragment() {
    private lateinit var binding: FragmentCleverDeclarationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCleverDeclarationBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(CleverDeclarationFragmentDirections.actionCleverDeclarationFragmentToHomeFragment())
        }
        binding.cleveraddbutton.setOnClickListener {
            Toast.makeText(context,"Ağıllı Bəyan mövcud deyil",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}