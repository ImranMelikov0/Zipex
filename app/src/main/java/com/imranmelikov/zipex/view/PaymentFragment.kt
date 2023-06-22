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
import com.imranmelikov.zipex.adapter.BalanceAdapter
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.databinding.FragmentPaymentAznBinding
import com.imranmelikov.zipex.databinding.FragmentPaymentBinding
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
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
        viewModel.getTotalBalanceTry()
        observeBalance()
        payFromBalance()

        arguments?.let {
            if (PaymentFragmentArgs.fromBundle(it).balanceAmountTry==4F){
                findNavController().popBackStack()
            }else if(PaymentFragmentArgs.fromBundle(it).balanceAmountTry==5F){
                findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToCartFragment2())
            }else if (PaymentFragmentArgs.fromBundle(it).balanceAmountTry==9F){
                findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToCartFragment2())
            }else{

            }
        }

        return binding.root
    }
    private fun observeBalance(){
        viewModel.balanceTotalTryLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {balanceTotalTry->
                getAmount=balanceTotalTry
                            if (viewModel.showFirst == false){
                                binding.paybutton.setOnClickListener {
                                    if (binding.addcartnumber.text.toString()
                                            .isEmpty() || binding.month.text.toString()
                                            .isEmpty() || binding.year.text.toString().isEmpty() ||
                                        binding.cvv.text.toString().isEmpty()
                                    ) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Məlumatları daxil edin",
                                            Toast.LENGTH_SHORT
                                        ).show()
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
                                        val price=viewModel.getDouble
                                        val totalTryPlus=balanceTotalTry.balanceTotal+price
                                        val decimalFormat = DecimalFormat("#.##")
                                        decimalFormat.roundingMode = RoundingMode.HALF_UP
                                        val roundedAmount = decimalFormat.format(totalTryPlus).toDouble()
                                            val currentDate = LocalDateTime.now()
                                            val formatter =
                                                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
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

                                        Toast.makeText(
                                                requireContext(),
                                                "success",
                                                Toast.LENGTH_SHORT
                                            ).show()
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
                    Toast.makeText(requireContext(),"Xəta baş verdi", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING->{

                }
            }
        })
    }
    private fun payFromBalance() {
        if (viewModel.showFirst == true) {
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
                    val currentDate = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate = currentDate.format(formatter)
                    val balanceTry = BalanceTry(formatDate, roundedAmount1, roundedAmount)
                    viewModel.insertBalanceTry(balanceTry)

                Toast.makeText(requireContext(), "Ugurlu", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
    }
}