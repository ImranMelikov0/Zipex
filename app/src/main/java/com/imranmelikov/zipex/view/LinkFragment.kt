package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentLinkBinding
import com.imranmelikov.zipex.databinding.FragmentTariffBinding
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.mvvm.LinkViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class LinkFragment : Fragment() {
private lateinit var binding: FragmentLinkBinding
private lateinit var viewModel:LinkViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLinkBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[LinkViewModel::class.java]
        arguments?.let {
            val StringCart=  LinkFragmentArgs.fromBundle(it).CartString
            if (StringCart.equals("String")){
                findNavController().navigate(LinkFragmentDirections.actionLinkFragmentToCartFragment())
            }
        }
        binding.back.setOnClickListener {
            findNavController().navigate(LinkFragmentDirections.actionLinkFragmentToHomeFragment())
        }
        binding.linkadd.setOnClickListener {
            val currentDate=LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
            val formatDate=currentDate.format(formatter)
            viewModel.makeLink(
                binding.editLink.text.toString(),binding.editCategory.text.toString(),
                binding.editquantity.text.toString().toIntOrNull(),binding.editcolor.text.toString(),
                binding.editsize.text.toString(),binding.editprice.text.toString().toDoubleOrNull(),
                binding.editcomment.text.toString(),formatDate,binding.editCountry.text.toString(),"Sığortalanmayıb","Ödənilməyib"
            )
            observeLink()
        }
        return binding.root
    }
    private fun observeLink(){
        viewModel.linkMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                    val customToast = CustomToast(requireContext())
                    customToast.showToast(it.message?:"Məlumatları daxil edin")
                }
                Status.SUCCESS->{
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Məlumat əlavə olundu")
                    binding.editLink.text.clear()
                    binding.editCategory.text.clear()
                    binding.editquantity.text.clear()
                    binding.editcolor.text.clear()
                    binding.editsize.text.clear()
                    binding.editprice.text.clear()
                    binding.editcomment.text.clear()
                    binding.editCountry.text.clear()
                    viewModel.resetLinkMessage()
                }
                Status.LOADING->{

                }
            }
        })
    }
}