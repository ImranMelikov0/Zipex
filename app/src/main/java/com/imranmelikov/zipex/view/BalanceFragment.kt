package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.adapter.BalanceAdapter
import com.imranmelikov.zipex.databinding.FragmentBalanceBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment @Inject constructor(
    private val balanceAdapter: BalanceAdapter
) : Fragment() {
    private lateinit var binding:FragmentBalanceBinding
    private lateinit var viewModel:BalanceViewModel
    private val amountTry=0.0
    private val amountAzn=0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBalanceBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[BalanceViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
        }
//            startInsert()


        binding.balanceRecyclerview.adapter=balanceAdapter
        binding.balanceRecyclerview.layoutManager=LinearLayoutManager(requireContext())
        viewModel.getBalanceTry()
        viewModel.getBalanceAzn()
        viewModel.getTotalBalanceTry()
        viewModel.getTotalBalanceAzn()
        observeBalance()
        return binding.root
    }
    private fun observeBalance(){
        viewModel.balanceTryLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{

                    binding.balanceRecyclerview.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE

                        viewModel.balanceTotalTryLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceTotalTry->
                            viewModel.balanceAznLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceAzn->
                                viewModel.balanceTotalAznLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceTotalAzn->
                                    it.data?.let {balanceTry->
                                        resourceBalanceTotalTry.data?.let {balanceTotalTry->
                                            resourceBalanceAzn.data?.let { balanceAzn->
                                                resourceBalanceTotalAzn.data?.let {balanceTotalAzn->
                                                    balanceAdapter.balanceList=balanceTry
                                                    balanceAdapter.balanceList1= balanceAzn
                                                    balanceAdapter.balanceList2=balanceTotalTry
                                                    balanceAdapter.balanceList3=balanceTotalAzn
                                                }
                                            }
                                        }
                                    }
                                })
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
//    private fun startInsert(){
//        val balanceTotalTry= BalanceTotalTry(amountTry)
//        val balanceTotalAzn= BalanceTotalAzn(amountAzn)
//        val currentDate= LocalDateTime.now()
//        val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
//        val formatDate=currentDate.format(formatter)
//        val balanceAzn= BalanceAzn(formatDate,0.0,amountAzn)
//        val balanceTry= BalanceTry(formatDate,0.0,amountTry)
//        viewModel.insertBalanceAzn(balanceAzn)
//        viewModel.insertBalanceTry(balanceTry)
//        viewModel.insertTotalBalanceAzn(balanceTotalAzn)
//        viewModel.insertTotalBalanceTry(balanceTotalTry)
//        viewModel.showFirst=false
//    }
}