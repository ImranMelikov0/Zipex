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
import com.imranmelikov.zipex.adapter.BalanceAdapter
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.databinding.FragmentPaymentAznBinding
import com.imranmelikov.zipex.databinding.FragmentPaymentBinding
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.mvvm.AdminViewModel
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.mvvm.CartViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class PaymentFragment @Inject constructor(
) : Fragment() {
    private lateinit var binding:FragmentPaymentBinding
    private lateinit var viewModel:BalanceViewModel
    private lateinit var cartViewModel:CartViewModel
    private lateinit var adminViewModel:AdminViewModel
    private lateinit var getAmount:BalanceTotalTry
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPaymentBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity()).get(BalanceViewModel::class.java)
        cartViewModel=ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        adminViewModel=ViewModelProvider(requireActivity())[AdminViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToHomeFragment())
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
        viewModel.getTotalBalanceTry()
        observeBalance()
        payFromBalance()

        arguments?.let {
            if (PaymentFragmentArgs.fromBundle(it).balanceAmountTry==4F){
                findNavController().popBackStack()
            }else if (PaymentFragmentArgs.fromBundle(it).balanceAmountTry==9F){
                findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToCartFragment2())
            }else{

            }
        }

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
        viewModel.balanceTotalTryLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {balanceTotalTry->
                getAmount=balanceTotalTry
                            if (viewModel.showFirst == false){
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
                                        val price=viewModel.getDouble
                                        val totalTryPlus=balanceTotalTry.balanceTotal+price
                                        val decimalFormat = DecimalFormat("#.##")
                                        decimalFormat.roundingMode = RoundingMode.HALF_UP
                                        val roundedAmount = decimalFormat.format(totalTryPlus).toDouble()
                                            val currentDate = LocalDateTime.now()
                                            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                                            val formatDate = currentDate.format(formatter)
                                            val insertBalanceTry =BalanceTry(formatDate, price, roundedAmount)
                                            viewModel.insertBalanceTry(insertBalanceTry)
                                        val totalTryMinus=roundedAmount-price
                                        val decimalFormat1 = DecimalFormat("#.##")
                                        decimalFormat1.roundingMode = RoundingMode.HALF_UP
                                        val roundedAmount1 = decimalFormat1.format(totalTryMinus).toDouble()
                                        val insertBalanceTry1 =BalanceTry(formatDate, price, roundedAmount1)
                                        viewModel.insertBalanceTry(insertBalanceTry1)

                                        val link=viewModel.getLink
                                        val updateCart=Link(link.url,link.category,link.count,link.color,link.size,link.price,link.comment,link.history,link.country,link.sigorta,"Ödənilib")
                                        updateCart.uuid=link.uuid
                                        cartViewModel.updateCart(updateCart)

                                        val adminLink=AdminLink(updateCart.url,updateCart.category,updateCart.count,updateCart.color,
                                            updateCart.size,updateCart.price,updateCart.comment,updateCart.history,updateCart.country,updateCart.sigorta,updateCart.payment)
                                        adminLink.uuid=updateCart.uuid
                                        adminViewModel.insertAdminLink(adminLink)

                                        val customToast = CustomToast(requireContext())
                                        customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                                            findNavController().popBackStack()
                                    }
                                }
                        }
            }
        })
        viewModel.updateBalanceTryLiveData.observe(viewLifecycleOwner, Observer {
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
    private fun payFromBalance() {
        if (viewModel.showFirst == true) {
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
                    val balanceTotalTryFromBalance =
                        viewModel.getDouble
                    val decimalFormat1 = DecimalFormat("#.##")
                    decimalFormat1.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount1 =
                        decimalFormat1.format(balanceTotalTryFromBalance).toDouble()
                    val defaultAmount =
                        balanceTotalTryFromBalance + getAmount.balanceTotal
                    val decimalFormat = DecimalFormat("#.##")
                    decimalFormat.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount = decimalFormat.format(defaultAmount).toDouble()
                    var totalTry = BalanceTotalTry(roundedAmount)
                    totalTry.uuid = getAmount.uuid

                    viewModel.updateBalanceTotalTry(totalTry)
                viewModel.getBalanceTotalTry=totalTry

                    val currentDate = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate = currentDate.format(formatter)
                    val balanceTry = BalanceTry(formatDate, roundedAmount1, roundedAmount)
                    viewModel.insertBalanceTry(balanceTry)

                val customToast = CustomToast(requireContext())
                customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                findNavController().popBackStack()
            }
        }
    }
    }
}