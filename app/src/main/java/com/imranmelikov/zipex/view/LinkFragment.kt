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
import com.imranmelikov.zipex.mvvm.LinkViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
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
        binding.back.setOnClickListener {
            findNavController().navigate(LinkFragmentDirections.actionLinkFragmentToHomeFragment())
        }
        binding.linkadd.setOnClickListener {
            val currentDate=LocalDate.now()
            val formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatDate=currentDate.format(formatter)
            viewModel.makeLink(
                binding.editLink.text.toString(),binding.editCategory.text.toString(),
                binding.editquantity.text.toString().toIntOrNull(),binding.editcolor.text.toString(),
                binding.editsize.text.toString(),binding.editprice.text.toString().toIntOrNull(),
                binding.editcomment.text.toString(),formatDate
            )
            observeLink()
        }
        return binding.root
    }
    private fun observeLink(){
        viewModel.linkMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                    Toast.makeText(requireContext(),it.message?:"Məlumatları daxil edin",Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS->{
                    Toast.makeText(requireContext(),"Məlumat əlavə olundu",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LinkFragmentDirections.actionLinkFragmentToCartFragment())
                    viewModel.resetLinkMessage()
                }
                Status.LOADING->{

                }
            }
        })
    }
}