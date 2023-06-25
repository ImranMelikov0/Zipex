package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.DebtHistoryAdapter
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import com.imranmelikov.zipex.databinding.FragmentPromoCodeBinding
import com.imranmelikov.zipex.model.DebtHistory
import com.imranmelikov.zipex.mvvm.DebtViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DebtHistoryFragment @Inject constructor(
    private val debtHistoryAdapter: DebtHistoryAdapter
) : Fragment() {
    private lateinit var binding: FragmentDebtHistoryBinding
    private lateinit var viewModel:DebtViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDebtHistoryBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(DebtViewModel::class.java)
        binding.back.setOnClickListener {
            findNavController().navigate(DebtHistoryFragmentDirections.actionDebtHistoryFragmentToHomeFragment())
        }
        binding.recyclerDebtazn.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerDebtazn.adapter=debtHistoryAdapter
        viewModel.getDebtHistory()
        observeDebtHistory()
        val myColor= ContextCompat.getColor(requireContext(), R.color.primary)


        binding.debthistoryAzn.setOnClickListener {
            binding.debtHistoryTry.setBackgroundColor(Color.WHITE)
            binding.debtHistoryTry.setTextColor(myColor)
            binding.debthistoryAzn.setTextColor(Color.WHITE)
            binding.debthistoryAzn.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)
            binding.recyclerDebtazn.visibility=View.VISIBLE

        }
       binding.debtHistoryTry.setOnClickListener {
            binding.debtHistoryTry.setBackgroundColor(myColor)
           binding.debtHistoryTry.setTextColor(Color.WHITE)
            binding.debthistoryAzn.setTextColor(myColor)
           binding.debthistoryAzn.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)
           binding.recyclerDebtazn.visibility=View.GONE
        }
        binding.debtHistoryUsd.setOnClickListener {
           binding.debtHistoryTry.setBackgroundColor(Color.WHITE)
            binding.debtHistoryTry.setTextColor(myColor)
           binding.debthistoryAzn.setTextColor(myColor)
            binding.debthistoryAzn.setBackgroundColor(Color.WHITE)
           binding.debtHistoryUsd.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setTextColor(Color.WHITE)
            binding.recyclerDebtazn.visibility=View.GONE
        }
        return binding.root
    }
    private fun observeDebtHistory(){
        viewModel.debtHistoryMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerDebtazn.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.recyclerDebtazn.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.SUCCESS->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerDebtazn.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        debtHistoryAdapter.debtHistoryList=it
                    }
                }
            }
        })
    }


}