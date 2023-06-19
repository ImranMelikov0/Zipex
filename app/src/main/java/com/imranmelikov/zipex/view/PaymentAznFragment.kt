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
import com.imranmelikov.zipex.databinding.FragmentCartUpdateBinding
import com.imranmelikov.zipex.databinding.FragmentPaymentAznBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class PaymentAznFragment : Fragment() {
    private lateinit var binding: FragmentPaymentAznBinding
    private lateinit var viewModel:BalanceViewModel
    private lateinit var getAmount:BalanceTotalAzn

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPaymentAznBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(BalanceViewModel::class.java)
        binding.back.setOnClickListener {
            findNavController().navigate(PaymentAznFragmentDirections.actionPaymentAznFragmentToBalanceFragment())
        }
        viewModel.getTotalBalanceAzn()
        observeBalance()

        binding.paybutton.setOnClickListener {
            if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||binding.year.text.toString().isEmpty()||
                binding.cvv.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Məlumatları daxil edin", Toast.LENGTH_SHORT).show()
            }else{
                arguments?.let {
                    val balanceTotalAznFromBalance=PaymentAznFragmentArgs.fromBundle(it).balanceAzn
                    val decimalFormat1 = DecimalFormat("#.##")
                    decimalFormat1.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount1 = decimalFormat1.format(balanceTotalAznFromBalance).toDouble()
                    val defaultAmount=balanceTotalAznFromBalance.toDouble()+ getAmount.balanceTotal
                    val decimalFormat = DecimalFormat("#.##")
                    decimalFormat.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount = decimalFormat.format(defaultAmount).toDouble()
                    var totalAzn= BalanceTotalAzn(roundedAmount)
                    totalAzn.uuid=getAmount.uuid

                    viewModel.updateBalanceTotalAzn(totalAzn)
                    val currentDate= LocalDateTime.now()
                    val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate=currentDate.format(formatter)
                    val balanceAzn= BalanceAzn(formatDate,roundedAmount1,roundedAmount)
                    viewModel.insertBalanceAzn(balanceAzn)
                }
                Toast.makeText(requireContext(),"Ugurlu", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
    private fun observeBalance(){
        viewModel.balanceTotalAznLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {
                getAmount=it
            }
        })
        viewModel.updateBalanceAznLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{

                }
                Status.ERROR->{
                    Toast.makeText(requireContext(),"Xeta bas verdi", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
            }
        })
    }
}