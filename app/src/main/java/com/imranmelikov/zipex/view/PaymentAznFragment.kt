package com.imranmelikov.zipex.view

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
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
import com.imranmelikov.zipex.model.CustomToast
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
        val ccEditText=binding.addcartnumber
        val maxLength = 19
        val inputFilter = InputFilter.LengthFilter(maxLength)
        ccEditText.filters = arrayOf(inputFilter)


        ccEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                val formattedInput = formatCreditCardNumber(input)

                if (formattedInput != input) {
                    ccEditText.setText(formattedInput)
                    ccEditText.setSelection(formattedInput.length)
                }
            }
        })
        val maxLength1 = 2
        val inputFilter1 = InputFilter.LengthFilter(maxLength1)
        binding.month.filters = arrayOf(inputFilter1)
        val maxLength2 = 2
        val inputFilter2 = InputFilter.LengthFilter(maxLength2)
        binding.year.filters = arrayOf(inputFilter2)
        val maxLength3 = 4
        val inputFilter3 = InputFilter.LengthFilter(maxLength3)
        binding.cvv.filters = arrayOf(inputFilter3)

        viewModel.getTotalBalanceAzn()
        observeBalance()
        payFromBalance()
        payFromDebt()



        return binding.root
    }
    private fun formatCreditCardNumber(input: String): String {
        val digitsOnly = input.replace("\\D".toRegex(), "")
        val formatted = StringBuilder()

        var segmentLengths = intArrayOf(4, 4, 4, 4) // Adjust segment lengths if needed

        var segmentIndex = 0
        var currentIndex = 0

        while (currentIndex < digitsOnly.length) {
            val segmentLength = segmentLengths[segmentIndex]
            val endIndex = currentIndex + segmentLength

            if (endIndex <= digitsOnly.length) {
                formatted.append(digitsOnly.substring(currentIndex, endIndex))
                currentIndex = endIndex

                if (currentIndex < digitsOnly.length) {
                    formatted.append("-")
                }

                segmentIndex++
            } else {
                formatted.append(digitsOnly.substring(currentIndex))
                break
            }
        }

        return formatted.toString()
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
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Xəta baş verdi")
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
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Məlumatları daxil edin")
                }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Cvv məlumatlarını düzgün daxil edin")
                }else if(binding.year.text.length<2||binding.year.text.length>2||binding.year.text.toString()<"23"||binding.year.text.toString()>"33"){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("İl məlumatlarını düzgün daxil edin")
                }else if(binding.month.text.length>2||binding.month.text.toString()<"01"||binding.month.text.toString()>"12"){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Ay məlumatlarını düzgün daxil edin")
                }else if(binding.month.text.length<2){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın")
                }else if(binding.addcartnumber.text.length>19||binding.addcartnumber.text.length<19){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Kart məlumatlarını düzgün daxil edin")
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
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
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
                            if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||binding.year.text.toString().isEmpty()||
                                binding.cvv.text.toString().isEmpty()){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Məlumatları daxil edin")
                            }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Cvv məlumatlarını düzgün daxil edin")
                            }else if(binding.year.text.length<2||binding.year.text.length>2||binding.year.text.toString()<"23"||binding.year.text.toString()>"33"){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("İl məlumatlarını düzgün daxil edin")
                            }else if(binding.month.text.length>2||binding.month.text.toString()<"01"||binding.month.text.toString()>"12"){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Ay məlumatlarını düzgün daxil edin")
                            }else if(binding.month.text.length<2){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın")
                            }else if(binding.addcartnumber.text.length>19||binding.addcartnumber.text.length<19){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Kart məlumatlarını düzgün daxil edin")
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
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                                findNavController().popBackStack()
                            }
                        }
                    }else    if (viewModel.showFirstDebt==3){
                        binding.paybutton.setOnClickListener {
                            if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||binding.year.text.toString().isEmpty()||
                                binding.cvv.text.toString().isEmpty()){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Məlumatları daxil edin")
                            }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Cvv məlumatlarını düzgün daxil edin")
                            }else if(binding.year.text.length<2||binding.year.text.length>2||binding.year.text.toString()<"23"||binding.year.text.toString()>"33"){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("İl məlumatlarını düzgün daxil edin")
                            }else if(binding.month.text.length>2||binding.month.text.toString()<"01"||binding.month.text.toString()>"12"){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Ay məlumatlarını düzgün daxil edin")
                            }else if(binding.month.text.length<2){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Kartınızın ay müddəti 10dan kiçikdirsə rəqəmin öncəsinə 0 artırın")
                            }else if(binding.addcartnumber.text.length>19||binding.addcartnumber.text.length<19){
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Kart məlumatlarını düzgün daxil edin")
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
                                val customToast = CustomToast(requireContext())
                                customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                                findNavController().popBackStack()
                            }
                        }
                    }
                }
        })

    }
}