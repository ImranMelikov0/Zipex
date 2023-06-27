package com.imranmelikov.zipex.view

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
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
import com.imranmelikov.zipex.model.CustomToast
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
    private lateinit var binding: FragmentPaymentUsdBinding
    private lateinit var viewModel: BalanceViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var getAmount: BalanceTotalUsd
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentUsdBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[BalanceViewModel::class.java]
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        adminViewModel = ViewModelProvider(requireActivity())[AdminViewModel::class.java]

        binding.back.setOnClickListener {
            findNavController().navigate(PaymentUsdFragmentDirections.actionPaymentUsdFragmentToHomeFragment())
        }
        val ccEditText = binding.addcartnumber
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

        val ccEditTextMY = binding.month
        val maxLengthMY = 5
        val inputFilterMY = InputFilter.LengthFilter(maxLengthMY)
        ccEditTextMY.filters = arrayOf(inputFilterMY)

        ccEditTextMY.addTextChangedListener(object : TextWatcher {
            var count = 0
            var isDeleting = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isDeleting = count > after
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val input = ccEditTextMY.text.toString()
                if (!isDeleting) {
                    val inputLength = input.length

                    if ((count <= inputLength) && (inputLength == 2)) {
                        if (input.substring(0, 2).toInt() > 12) {
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Ay və İl məlumatlarını düzgün daxil edin")
                            ccEditTextMY.setText("")
                        } else {
                            ccEditTextMY.setText(input + "/")
                        }

                        val pos = ccEditTextMY.text.length
                        ccEditTextMY.setSelection(pos)
                    } else if ((count >= inputLength) && (inputLength == 2)) {
                        if (input.substring(0, 2).toInt() > 12) {
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Ay və İl məlumatlarını düzgün daxil edin")
                            ccEditTextMY.setText("")
                        } else {
                            ccEditTextMY.setText(input.substring(0, input.length - 1))
                        }

                        val pos = ccEditTextMY.text.length
                        ccEditTextMY.setSelection(pos)
                    }

                    if (inputLength == 5) {
                        val month = input.substring(0, 2).toInt()
                        val year = input.substring(3, 5).toInt()

                        if (month > 12) {
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("İldə 12 ay vardır")
                            ccEditTextMY.setText(input.substring(0, 2) + "/")
                            val pos = ccEditTextMY.text.length
                            ccEditTextMY.setSelection(pos)
                        }  else if (input[0] == '0' && input[1] == '0') {
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Ay yuvarlaq ola bilər ancaq sıfır ola bilməz")
                            ccEditTextMY.setText("")
                            val pos = ccEditTextMY.text.length
                            ccEditTextMY.setSelection(pos)
                        } else if (year < 23) {
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("İl 23dən az ola bilməz")
                            ccEditTextMY.setText(input.substring(0, 2) + "/")
                            val pos = ccEditTextMY.text.length
                            ccEditTextMY.setSelection(pos)
                        }else if(year>33){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("İl 33dən cox ola bilməz")
                            ccEditTextMY.setText(input.substring(0, 2) + "/")
                            val pos = ccEditTextMY.text.length
                            ccEditTextMY.setSelection(pos)
                        } else if (input[2] != '/') {
                            ccEditTextMY.setText(input.substring(0, 2) + "/" + input.substring(3, 5))
                            val pos = ccEditTextMY.text.length
                            ccEditTextMY.setSelection(pos)
                        }
                    }
                }

                count = ccEditTextMY.text.toString().length
            }
             })

            val maxLength3 = 4
        val inputFilter3 = InputFilter.LengthFilter(maxLength3)
        binding.cvv.filters = arrayOf(inputFilter3)
        viewModel.getTotalBalanceUsd()
        observeBalance()
        payFromBalance()
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
                    formatted.append(" ")
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
        viewModel.balanceTotalUsdLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {balanceTotalUsd->
                getAmount=balanceTotalUsd
                if (viewModel.showFirst == false){
                    binding.paybutton.setOnClickListener {
                        if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||
                            binding.cvv.text.toString().isEmpty()){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Məlumatları daxil edin")
                        }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Cvv məlumatlarını düzgün daxil edin")
                        }else if(binding.month.text.length>5){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Ay və İl məlumatlarını düzgün daxil edin")
                        }else if(binding.month.text.length<5){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("/-i silmisinizsə ay və ili yenidən qeyd edin")
                        }else if(binding.addcartnumber.text.length>19||binding.addcartnumber.text.length<19){
                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Kart məlumatlarını düzgün daxil edin boşluqları silmisinizsə əlavə edin vəya kart nömrəsini yenidən yazın")
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

                            val customToast = CustomToast(requireContext())
                            customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
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
                if (binding.addcartnumber.text.toString().isEmpty()||binding.month.text.toString().isEmpty()||
                    binding.cvv.text.toString().isEmpty()){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Məlumatları daxil edin")
                }else if(binding.cvv.text.length<3||binding.cvv.text.length>4){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Cvv məlumatlarını düzgün daxil edin")
                }else if(binding.month.text.length>5){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Ay və İl məlumatlarını düzgün daxil edin")
                }else if(binding.month.text.length<5){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("/-i silmisinizsə ay və ili yenidən qeyd edin")
                }else if(binding.addcartnumber.text.length>19||binding.addcartnumber.text.length<19){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Kart məlumatlarını düzgün daxil edin")
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

                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                    findNavController().popBackStack()
                }
            }
        }
    }
}