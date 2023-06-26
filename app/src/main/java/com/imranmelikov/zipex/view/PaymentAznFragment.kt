package com.imranmelikov.zipex.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.DebtAdapter
import com.imranmelikov.zipex.databinding.FragmentPaymentAznBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.DebtHistory
import com.imranmelikov.zipex.model.DebtTotal
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.mvvm.DebtViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class PaymentAznFragment : Fragment() {
    private lateinit var binding: FragmentPaymentAznBinding
    private lateinit var viewModel:BalanceViewModel
    private lateinit var getAmount:BalanceTotalAzn
    private lateinit var debtViewModel:DebtViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPaymentAznBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(BalanceViewModel::class.java)
        debtViewModel=ViewModelProvider(requireActivity()).get(DebtViewModel::class.java)
        binding.back.setOnClickListener {
            findNavController().navigate(PaymentAznFragmentDirections.actionPaymentAznFragmentToHomeFragment())
        }
        viewModel.getTotalBalanceAzn()
        observeBalance()
        payFromBalance()
        payFromDebt()

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
                    Toast.makeText(requireContext(),"Xəta baş verdi", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
            }
        })
    }
    private fun payFromBalance(){
        if (viewModel.showFirstDebt==1){
            binding.paybutton.setOnClickListener {
                if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||binding.year.text.toString().isEmpty()||
                    binding.cvv.text.toString().isEmpty()){
                    Toast.makeText(requireContext(),"Məlumatları daxil edin", Toast.LENGTH_SHORT).show()
                }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                    Toast.makeText(requireContext(),"Cvv məlumatlarını düzgün daxil edin",Toast.LENGTH_SHORT).show()
                }else if(binding.year.text.length<2||binding.year.text.length>2||binding.year.text.toString()<"23"||binding.year.text.toString()>"33"){
                    Toast.makeText(requireContext(),"İl məlumatlarını düzgün daxil edin",Toast.LENGTH_SHORT).show()
                }else if(binding.month.text.length>2||binding.month.text.toString()<"01"||binding.month.text.toString()>"12"){
                    Toast.makeText(requireContext(),"Ay məlumatlarını düzgün daxil edin",Toast.LENGTH_SHORT).show()
                }else if(binding.month.text.length<2){
                    Toast.makeText(requireContext(),"Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın",Toast.LENGTH_LONG).show()
                }else if(binding.addcartnumber.text.length>16||binding.addcartnumber.text.length<16){
                    Toast.makeText(requireContext(),"Kart məlumatlarını düzgün daxil edin",Toast.LENGTH_SHORT).show()
                }
                else{
                    val balanceTotalAznFromBalance=viewModel.getDouble
                    val decimalFormat1 = DecimalFormat("#.##")
                    decimalFormat1.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount1 = decimalFormat1.format(balanceTotalAznFromBalance).toDouble()
                    val defaultAmount=balanceTotalAznFromBalance+ getAmount.balanceTotal
                    val decimalFormat = DecimalFormat("#.##")
                    decimalFormat.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount = decimalFormat.format(defaultAmount).toDouble()
                    var totalAzn= BalanceTotalAzn(roundedAmount)
                    totalAzn.uuid=getAmount.uuid

                    viewModel.updateBalanceTotalAzn(totalAzn)
                    viewModel.getBalanceTotalAzn=totalAzn

                    val currentDate= LocalDateTime.now()
                    val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate=currentDate.format(formatter)
                    val balanceAzn= BalanceAzn(formatDate,roundedAmount1,roundedAmount)
                    viewModel.insertBalanceAzn(balanceAzn)
                    Toast.makeText(requireContext(),"Əməliyyat uğurla yerinə yetirildi", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

            }
        }
    }
    private fun payFromDebt(){
        debtViewModel.debtTotalMsg.observe(viewLifecycleOwner, Observer {resourse->
            resourse.data?.let { debtTotal->
                    if (viewModel.showFirstDebt==2){
                        binding.paybutton.setOnClickListener {
                            if (binding.addcartnumber.text.toString().isEmpty() || binding.month.text.toString()
                                    .isEmpty() || binding.year.text.toString().isEmpty() ||
                                binding.cvv.text.toString().isEmpty()
                            ) {
                                Toast.makeText(requireContext(), "Məlumatları daxil edin", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (binding.cvv.text.length < 3 || binding.cvv.text.length > 4) {
                                Toast.makeText(
                                    requireContext(),
                                    "Cvv məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.year.text.length < 2 || binding.year.text.length > 2 || binding.year.text.toString() < "23" || binding.year.text.toString() > "33") {
                                Toast.makeText(
                                    requireContext(),
                                    "İl məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.month.text.length > 2 || binding.month.text.toString() < "01" || binding.month.text.toString() > "12") {
                                Toast.makeText(
                                    requireContext(),
                                    "Ay məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.month.text.length < 2) {
                                Toast.makeText(
                                    requireContext(),
                                    "Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else if (binding.addcartnumber.text.length > 16 || binding.addcartnumber.text.length < 16) {
                                Toast.makeText(
                                    requireContext(),
                                    "Kart məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val deleteDebt=viewModel.getDebt
                                val debtHistory=DebtHistory(deleteDebt.history,deleteDebt.amount)
                                debtViewModel.insertDebtHistory(debtHistory)

                               val totalDebt= debtTotal.debtTotal-deleteDebt.amount
                                val decimalFormat1 = DecimalFormat("#.##")
                                decimalFormat1.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount1 = decimalFormat1.format(totalDebt).toDouble()

                                val updateTotalDebt=DebtTotal(roundedAmount1)
                                updateTotalDebt.uuid=debtTotal.uuid
                                debtViewModel.updateDebtTotal(updateTotalDebt)

                                debtViewModel.getDebtTotal=updateTotalDebt

                                debtViewModel.deleteDebt(deleteDebt)

                                val totalAzn=getAmount.balanceTotal+deleteDebt.amount
                                val decimalFormat2 = DecimalFormat("#.##")
                                decimalFormat2.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount2 = decimalFormat2.format(totalAzn).toDouble()

                                val currentDate = LocalDateTime.now()
                                val formatter =DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                                val formatDate =currentDate.format(formatter)
                                val balanceAzn=BalanceAzn(formatDate,deleteDebt.amount,roundedAmount2)
                                viewModel.insertBalanceAzn(balanceAzn)

                               val resetTotalAznDouble= roundedAmount2-deleteDebt.amount
                                val decimalFormat3 = DecimalFormat("#.##")
                                decimalFormat3.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount3 = decimalFormat3.format(resetTotalAznDouble).toDouble()

                                val insertBalanceAzn=BalanceAzn(formatDate,deleteDebt.amount,roundedAmount3)
                                viewModel.insertBalanceAzn(insertBalanceAzn)
                                Toast.makeText(requireContext(),"Əməliyyat uğurla yerinə yetirildi", Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                        }
                    }else    if (viewModel.showFirstDebt==3){
                        binding.paybutton.setOnClickListener {
                            if (binding.addcartnumber.text.toString().isEmpty() || binding.month.text.toString()
                                    .isEmpty() || binding.year.text.toString().isEmpty() ||
                                binding.cvv.text.toString().isEmpty()
                            ) {
                                Toast.makeText(requireContext(), "Məlumatları daxil edin", Toast.LENGTH_SHORT)
                                    .show()
                            } else if (binding.cvv.text.length < 3 || binding.cvv.text.length > 4) {
                                Toast.makeText(
                                    requireContext(),
                                    "Cvv məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.year.text.length < 2 || binding.year.text.length > 2 || binding.year.text.toString() < "23" || binding.year.text.toString() > "33") {
                                Toast.makeText(
                                    requireContext(),
                                    "İl məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.month.text.length > 2 || binding.month.text.toString() < "01" || binding.month.text.toString() > "12") {
                                Toast.makeText(
                                    requireContext(),
                                    "Ay məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (binding.month.text.length < 2) {
                                Toast.makeText(
                                    requireContext(),
                                    "Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else if (binding.addcartnumber.text.length > 16 || binding.addcartnumber.text.length < 16) {
                                Toast.makeText(
                                    requireContext(),
                                    "Kart məlumatlarını düzgün daxil edin",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val totalDebt= viewModel.getDouble
                                val updateTotalDebtDouble=totalDebt-debtTotal.debtTotal
                                val decimalFormat1 = DecimalFormat("#.##")
                                decimalFormat1.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount1 = decimalFormat1.format(updateTotalDebtDouble).toDouble()

                                val updateTotalDebt=DebtTotal(roundedAmount1)
                                updateTotalDebt.uuid=debtTotal.uuid
                                debtViewModel.updateDebtTotal(updateTotalDebt)

                                debtViewModel.getDebtTotal=updateTotalDebt

                                val currentDate= LocalDateTime.now()
                                val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                                val formatDate=currentDate.format(formatter)
                                val insertDebtHistory=DebtHistory(formatDate,totalDebt)
                                debtViewModel.insertDebtHistory(insertDebtHistory)

                                debtViewModel.deleteDebtAll()

                               val balanceTotalAzn= getAmount.balanceTotal+totalDebt
                                val decimalFormat2 = DecimalFormat("#.##")
                                decimalFormat2.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount2 = decimalFormat2.format(balanceTotalAzn).toDouble()
                                val balanceAzn=BalanceAzn(formatDate,totalDebt,roundedAmount2)
                                viewModel.insertBalanceAzn(balanceAzn)

                                val totalBalanceAzn=balanceTotalAzn-totalDebt
                                val decimalFormat3 = DecimalFormat("#.##")
                                decimalFormat3.roundingMode = RoundingMode.HALF_UP
                                val roundedAmount3 = decimalFormat3.format(totalBalanceAzn).toDouble()
                                val aznBalance=BalanceAzn(formatDate,totalDebt,roundedAmount3)
                                viewModel.insertBalanceAzn(aznBalance)
                                Toast.makeText(requireContext(),"Əməliyyat uğurla yerinə yetirildi", Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
        })

    }
}