package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.AdminAdapter
import com.imranmelikov.zipex.databinding.FragmentAdminBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.mvvm.AdminViewModel
import com.imranmelikov.zipex.mvvm.CartViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdminFragment @Inject constructor(
    private val adminAdapter: AdminAdapter
) : Fragment() {
    private lateinit var binding: FragmentAdminBinding
    private lateinit var viewModel:AdminViewModel
    private lateinit var cartViewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(AdminViewModel::class.java)
        cartViewModel=ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        binding.back.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToHomeFragment())
        }
        binding.buttonadmin.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminDetailFragment())
        }
        binding.buttonadminnews.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminNewsFragment())
        }
        binding.buttonadminnotification.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToAdminNotificationFragment2())
        }
        binding.recyclerviewAdmin.adapter=adminAdapter
        binding.recyclerviewAdmin.layoutManager=LinearLayoutManager(requireContext())
       onItemClick()
        viewModel.getAdminLink()
        observeAdminLink()
        return binding.root
    }

    private fun onItemClick(){
        adminAdapter.onItemClickConfirm={
            viewModel.insertOrder1(it)
        }
        adminAdapter.onItemClickReject={
            val link=Link(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment)
            link.uuid=it.uuid
            cartViewModel.deleteCart(link)
            viewModel.deleteAdminLink(it)
        }
        adminAdapter.onItemClickConfirmDelete={
            val link=Link(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment)
            link.uuid=it.uuid
            cartViewModel.deleteCart(link)
            viewModel.deleteAdminLink(it)
        }
    }

    private fun observeAdminLink(){
        viewModel.adminLinkMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.recyclerviewAdmin.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminAdapter.AdminList=it
                    }
                }
                Status.LOADING->{
                    binding.recyclerviewAdmin.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.recyclerviewAdmin.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
            }
        })
    }

}