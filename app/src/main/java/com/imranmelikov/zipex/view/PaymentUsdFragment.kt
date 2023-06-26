package com.imranmelikov.zipex.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentPaymentUsdBinding
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTotalUsd
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.BalanceUsd
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.mvvm.AdminViewModel
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.mvvm.CartViewModel
import com.imranmelikov.zipex.util.Status
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PaymentUsdFragment : Fragment() {
 private lateinit var binding:FragmentPaymentUsdBinding
private lateinit var viewModel:BalanceViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var getAmount: BalanceTotalUsd
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPaymentUsdBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[BalanceViewModel::class.java]
        cartViewModel=ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        adminViewModel=ViewModelProvider(requireActivity())[AdminViewModel::class.java]

        binding.back.setOnClickListener {
            findNavController().navigate(PaymentUsdFragmentDirections.actionPaymentUsdFragmentToHomeFragment())
        }
        viewModel.getTotalBalanceUsd()
        observeBalance()
        payFromBalance()
        return binding.root
    }

    private fun observeBalance(){
        viewModel.balanceTotalUsdLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {balanceTotalUsd->
                getAmount=balanceTotalUsd
                if (viewModel.showFirst == false){
                    binding.paybutton.setOnClickListener {
                        if (binding.addcartnumber.text.toString()
                                .isEmpty() || binding.month.text.toString().isEmpty()||
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
                            val totalUsdPlus=balanceTotalUsd.balanceTotal+price
                            val decimalFormat = DecimalFormat("#.##")
                            decimalFormat.roundingMode = RoundingMode.HALF_UP
                            val roundedAmount = decimalFormat.format(totalUsdPlus).toDouble()
                            val currentDate = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                            val formatDate = currentDate.format(formatter)
                            val insertBalanceUsd = BalanceUsd(formatDate, price, roundedAmount)
                            viewModel.insertBalanceUsd(insertBalanceUsd)
                            val totalUsdMinus=roundedAmount-price
                            val decimalFormat1 = DecimalFormat("#.##")
                            decimalFormat1.roundingMode = RoundingMode.HALF_UP
                            val roundedAmount1 = decimalFormat1.format(totalUsdMinus).toDouble()
                            val insertBalanceUsd1 = BalanceUsd(formatDate, price, roundedAmount1)
                            viewModel.insertBalanceUsd(insertBalanceUsd1)

                            val link=viewModel.getLink
                            val updateCart= Link(link.url,link.category,link.count,link.color,link.size,link.price,link.comment,link.history,link.country,link.sigorta,"Ödənilib")
                            updateCart.uuid=link.uuid
                            cartViewModel.updateCart(updateCart)

                            val adminLink= AdminLink(updateCart.url,updateCart.category,updateCart.count,updateCart.color,
                                updateCart.size,updateCart.price,updateCart.comment,updateCart.history,updateCart.country,updateCart.sigorta,updateCart.payment)
                            adminLink.uuid=updateCart.uuid
                            adminViewModel.insertAdminLink(adminLink)

                            Toast.makeText(requireContext(),"Əməliyyat uğurla yerinə yetirildi", Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        })
        viewModel.updateBalanceUsdLiveData.observe(viewLifecycleOwner, Observer {
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
                        .isEmpty()||
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
                }  else if (binding.month.text.length > 2 || binding.month.text.toString() < "01" || binding.month.text.toString() > "12") {
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
                    val balanceTotalUsdFromBalance =
                        viewModel.getDouble
                    val decimalFormat1 = DecimalFormat("#.##")
                    decimalFormat1.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount1 =
                        decimalFormat1.format(balanceTotalUsdFromBalance).toDouble()
                    val defaultAmount =
                        balanceTotalUsdFromBalance + getAmount.balanceTotal
                    val decimalFormat = DecimalFormat("#.##")
                    decimalFormat.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount = decimalFormat.format(defaultAmount).toDouble()
                    var totalUsd = BalanceTotalUsd(roundedAmount)
                    totalUsd.uuid = getAmount.uuid

                    viewModel.updateBalanceTotalUsd(totalUsd)
                    viewModel.getBalanceTotalUsd = totalUsd

                    val currentDate = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate = currentDate.format(formatter)
                    val balanceUsd = BalanceUsd(formatDate, roundedAmount1, roundedAmount)
                    viewModel.insertBalanceUsd(balanceUsd)

                    Toast.makeText(requireContext(),"Əməliyyat uğurla yerinə yetirildi", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }
}