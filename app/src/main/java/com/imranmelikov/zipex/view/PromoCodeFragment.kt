package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentPromoCodeBinding
import com.imranmelikov.zipex.databinding.FragmentTrendyolConfirmBinding

class PromoCodeFragment : Fragment() {
    private lateinit var binding: FragmentPromoCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPromoCodeBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(PromoCodeFragmentDirections.actionPromoCodeFragmentToHomeFragment())
        }
        binding.promoButton.setOnClickListener {
            Toast.makeText(context,"Promo kodunuz yoxdur",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}