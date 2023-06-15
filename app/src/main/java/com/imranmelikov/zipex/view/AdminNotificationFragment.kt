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
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding
import com.imranmelikov.zipex.databinding.FragmentAdminNotificationBinding
import com.imranmelikov.zipex.mvvm.NotificationViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminNotificationFragment : Fragment() {
    private lateinit var binding: FragmentAdminNotificationBinding
    private lateinit var viewModel:NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminNotificationBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[NotificationViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(AdminNotificationFragmentDirections.actionAdminNotificationFragment2ToAdminFragment())
        }
        binding.button2.setOnClickListener {
            viewModel.makeNotification(binding.editTextText.text.toString()
                ,binding.editTextText2.text.toString())
        }
        observeNotification()
        return binding.root
    }
    private fun observeNotification(){
        viewModel.notificationErrorMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                    Toast.makeText(requireContext(),it.message?:"Enter Values",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    viewModel.resetNotificationErrorMessage()
                    findNavController().popBackStack()
                }
            }
        })
    }
}