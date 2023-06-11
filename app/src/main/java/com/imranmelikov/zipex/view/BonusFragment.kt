package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentBonusBinding
import com.imranmelikov.zipex.databinding.FragmentCourierBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.Date

@AndroidEntryPoint
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
        val text="Balans (Bonuslar): 0"
        val spannableString=SpannableString(text)
        val endLength=19
        val color=R.color.primary
        spannableString.setSpan(ForegroundColorSpan(Color.BLACK),0,endLength,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(),color)),endLength,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textview.text=spannableString
        return binding.root
    }
}