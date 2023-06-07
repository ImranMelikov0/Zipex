package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentBonusBinding
import com.imranmelikov.zipex.databinding.FragmentCourierBinding

class BonusFragment : Fragment() {
    private lateinit var binding: FragmentBonusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBonusBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(BonusFragmentDirections.actionBonusFragmentToHomeFragment())
        }
        binding.bonusShare.setOnClickListener {
            Toast.makeText(context,"Paylaşmaq qadağandır!!!",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}