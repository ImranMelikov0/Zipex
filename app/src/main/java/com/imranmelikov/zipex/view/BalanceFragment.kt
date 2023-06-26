package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.BalanceAdapter
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.databinding.FragmentBalanceBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment @Inject constructor(
    private val balanceAdapter: BalanceAdapter
) : Fragment() {
    private lateinit var binding:FragmentBalanceBinding
    private lateinit var viewModel:BalanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBalanceBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[BalanceViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.balanceRecyclerview.visibility=View.GONE
            binding.cryptoErrorText.visibility=View.GONE
            binding.cryptoProgressBar.visibility=View.VISIBLE
            viewModel.getBalanceTry()
            viewModel.getBalanceAzn()
            viewModel.getBalanceUsd()
            viewModel.getTotalBalanceUsd()
            viewModel.getTotalBalanceTry()
            viewModel.getTotalBalanceAzn()
            binding.swipeRefreshLayout.isRefreshing=false
        }

        binding.balanceRecyclerview.adapter=balanceAdapter
        binding.balanceRecyclerview.layoutManager=LinearLayoutManager(requireContext())
        viewModel.getBalanceTry()
        viewModel.getBalanceAzn()
        viewModel.getBalanceUsd()
        viewModel.getTotalBalanceUsd()
        viewModel.getTotalBalanceTry()
        viewModel.getTotalBalanceAzn()
        observeBalance()
        addBalance()

        return binding.root
    }
    private fun observeBalance(){
        viewModel.balanceTryLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.balanceRecyclerview.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    viewModel.balanceUsdLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceUsd->
                            viewModel.balanceAznLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceAzn->
                                    it.data?.let {balanceTry->
                                            resourceBalanceAzn.data?.let { balanceAzn->
                                                resourceBalanceUsd.data?.let {
                                                    balanceAdapter.balanceList2=it
                                                    balanceAdapter.balanceList=balanceTry
                                                    balanceAdapter.balanceList1= balanceAzn
                                                }

                                            }
                                        }
                            })
                        })
                }
                Status.LOADING->{
                    binding.balanceRecyclerview.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.balanceRecyclerview.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
            }
        })

    }
    private fun addBalance(){
        binding.balanceItemService.setOnClickListener {view->
            viewModel.onItemClick="b"
            balanceAdapter.showFirst=2
            Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
        }
        binding.balanceItemOrder.setOnClickListener {view->
            viewModel.onItemClick="c"
            balanceAdapter.showFirst=1
            Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
        }
            binding.debtHistoryUsd.setOnClickListener {view->
                viewModel.onItemClick="d"
                balanceAdapter.showFirst=3
                Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
            }
        val myColor= ContextCompat.getColor(requireContext(), R.color.primary)
        if (balanceAdapter.showFirst==1){
            binding.balanceItemService.setBackgroundColor(Color.WHITE)
            binding.balanceItemService.setTextColor(myColor)
            binding.balanceItemOrder.setTextColor(Color.WHITE)
            binding.balanceItemOrder.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)

            binding.balanceItemAddbalancebutton.setOnClickListener {
                if (binding.balanceItemAddbalance.text.toString().isEmpty()){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Balansı qeyd edin")
                }else{
                    val amount=binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(BalanceFragmentDirections.actionBalanceFragmentToPaymentFragment())
                    viewModel.showFirst = true
                    viewModel.getDouble=amount.toDouble()
                    binding.balanceItemAddbalance.text.clear()
                }
            }

          binding.balanceTitleBalance.text="Balans : ${viewModel.getBalanceTotalTry.balanceTotal} TL"
        }else if (balanceAdapter.showFirst==2){
            binding.balanceItemService.setBackgroundColor(myColor)
            binding.balanceItemService.setTextColor(Color.WHITE)
            binding.balanceItemOrder.setTextColor(myColor)
            binding.balanceItemOrder.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)

            binding.balanceItemAddbalancebutton.setOnClickListener {
                if (binding.balanceItemAddbalance.text.toString().isEmpty()) {
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Balansı qeyd edin")
                } else {
                    val amount = binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(
                        BalanceFragmentDirections.actionBalanceFragmentToPaymentAznFragment()
                    )
                    viewModel.showFirstDebt=1
                    viewModel.getDouble=amount.toDouble()
                    binding.balanceItemAddbalance.text.clear()
                }
            }

            binding.balanceTitleBalance.text="Balans : ${viewModel.getBalanceTotalAzn.balanceTotal} AZN"
        }else{
            binding.debtHistoryUsd.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setTextColor(Color.WHITE)
            binding.balanceItemService.setBackgroundColor(Color.WHITE)
            binding.balanceItemService.setTextColor(myColor)
            binding.balanceItemOrder.setTextColor(myColor)
            binding.balanceItemOrder.setBackgroundColor(Color.WHITE)

            binding.balanceItemAddbalancebutton.setOnClickListener {
                if (binding.balanceItemAddbalance.text.toString().isEmpty()) {
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Balansı qeyd edin")
                } else {
                    val amount = binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(
                        BalanceFragmentDirections.actionBalanceFragmentToPaymentUsdFragment()
                    )
                    viewModel.showFirst=true
                    viewModel.getDouble=amount.toDouble()
                    binding.balanceItemAddbalance.text.clear()
                }
            }

            binding.balanceTitleBalance.text="Balans : ${viewModel.getBalanceTotalUsd.balanceTotal} USD"
        }

    }
}