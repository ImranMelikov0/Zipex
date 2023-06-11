package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentMailBinding
import com.imranmelikov.zipex.databinding.FragmentPromoCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MailFragment : Fragment() {
    private lateinit var binding: FragmentMailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMailBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(MailFragmentDirections.actionMailFragmentToHomeFragment())
        }
        val myColor=ContextCompat.getColor(requireContext(),R.color.primary)
        binding.button.setOnClickListener {
            Toast.makeText(context,"Poçt xidməti mövcud deyil",Toast.LENGTH_SHORT).show()
        }
        binding.buttonsifaris.setOnClickListener {
            binding.buttonpoct.setBackgroundColor(Color.WHITE)
            binding.buttonpoct.setTextColor(myColor)
            binding.buttonsifaris.setTextColor(Color.WHITE)
            binding.buttonsifaris.setBackgroundColor(myColor)
        }
        binding.buttonpoct.setOnClickListener {
            binding.buttonsifaris.setBackgroundColor(Color.WHITE)
            binding.buttonpoct.setBackgroundColor(myColor)
            binding.buttonsifaris.setTextColor(myColor)
            binding.buttonpoct.setTextColor(Color.WHITE)
        }
        return binding.root
    }
}