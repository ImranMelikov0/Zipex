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

                            viewModel.balanceAznLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceAzn->
                                    it.data?.let {balanceTry->
                                            resourceBalanceAzn.data?.let { balanceAzn->
                                                    balanceAdapter.balanceList=balanceTry
                                                    balanceAdapter.balanceList1= balanceAzn
                                            }
                                        }
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
        val myColor= ContextCompat.getColor(requireContext(), R.color.primary)
        if (balanceAdapter.showFirst==1){
            binding.balanceItemService.setBackgroundColor(Color.WHITE)
            binding.balanceItemService.setTextColor(myColor)
            binding.balanceItemOrder.setTextColor(Color.WHITE)
            binding.balanceItemOrder.setBackgroundColor(myColor)

            binding.balanceItemAddbalancebutton.setOnClickListener {
                if (binding.balanceItemAddbalance.text.toString().isEmpty()){
                    Toast.makeText(requireContext(),"Balansı qeyd edin",Toast.LENGTH_SHORT).show()
                }else{
                    val amount=binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(BalanceFragmentDirections.actionBalanceFragmentToPaymentFragment())
                    viewModel.showFirst = true
                    viewModel.getDouble=amount.toDouble()
                    binding.balanceItemAddbalance.text.clear()
                }
            }
           binding.balanceItemService.setOnClickListener {view->
                balanceAdapter.showFirst=2
                Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
                viewModel.onItemClick="b"
            }
          binding.balanceTitleBalance.text="Balans : ${viewModel.getBalanceTotalTry.balanceTotal} TL"
        }else if (balanceAdapter.showFirst==2){
            binding.balanceItemService.setBackgroundColor(myColor)
            binding.balanceItemService.setTextColor(Color.WHITE)
            binding.balanceItemOrder.setTextColor(myColor)
            binding.balanceItemOrder.setBackgroundColor(Color.WHITE)

            binding.balanceItemAddbalancebutton.setOnClickListener {
                if (binding.balanceItemAddbalance.text.toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Balansı qeyd edin", Toast.LENGTH_SHORT)
                        .show()
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
          binding.balanceItemOrder.setOnClickListener {view->
                balanceAdapter.showFirst=1
                Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
                viewModel.onItemClick="c"
            }

            binding.balanceTitleBalance.text="Balans : ${viewModel.getBalanceTotalAzn.balanceTotal} AZN"
        }else{

        }

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