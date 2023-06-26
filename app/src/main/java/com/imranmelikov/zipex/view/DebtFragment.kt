package com.imranmelikov.zipex.view

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.DebtAdapter
import com.imranmelikov.zipex.databinding.FragmentDebtBinding
import com.imranmelikov.zipex.databinding.FragmentDebtHistoryBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.model.DebtHistory
import com.imranmelikov.zipex.model.DebtTotal
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.mvvm.DebtViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class DebtFragment @Inject constructor(
    private val debtAdapter: DebtAdapter
) : Fragment() {
    private lateinit var binding: FragmentDebtBinding
    private lateinit var viewModel:DebtViewModel
    private lateinit var balanceViewModel:BalanceViewModel
   private lateinit var getTotalAzn:BalanceTotalAzn
   private lateinit var getDebtTotal:DebtTotal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDebtBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[DebtViewModel::class.java]
        balanceViewModel=ViewModelProvider(requireActivity()).get(BalanceViewModel::class.java)
        binding.back.setOnClickListener {
            findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToHomeFragment())
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerviewDebt.visibility=View.GONE
            binding.cryptoErrorText.visibility=View.GONE
            binding.cryptoProgressBar.visibility=View.VISIBLE
            viewModel.getDebt()
            viewModel.getDebtTotal()
            balanceViewModel.getTotalBalanceAzn()
            binding.swipeRefreshLayout.isRefreshing=false
        }
        binding.recyclerviewDebt.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerviewDebt.adapter=debtAdapter
        viewModel.getDebt()
        viewModel.getDebtTotal()
        balanceViewModel.getTotalBalanceAzn()
        observeDebt()
        observeDebtTotal()
        observeTotalAzn()
        design()
        paySingle()

        onlineClick()
        payAll()

        if (viewModel.getDebtTotal.debtTotal!=0.0){
            binding.debtPayall.isEnabled=true
        }else{
        }
        return binding.root
    }
    private fun observeTotalAzn(){
        balanceViewModel.balanceTotalAznLiveData.observe(viewLifecycleOwner, Observer {
            it.data?.let {
                getTotalAzn=it
                balanceViewModel.getBalanceTotalAzn=it
            }
        })
    }
    private fun observeDebt(){
        viewModel.debtMsg.observe(viewLifecycleOwner, Observer {debt->
            when(debt.status){
                Status.LOADING->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerviewDebt.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.recyclerviewDebt.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.SUCCESS->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerviewDebt.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                    debt.data?.let {
                        debtAdapter.debtList=it
                    }
                }
            }
        })
    }
    private fun observeDebtTotal(){
        viewModel.debtTotalMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerviewDebt.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.recyclerviewDebt.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.SUCCESS->{
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.recyclerviewDebt.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        getDebtTotal=it
                    }
                }
            }
        })
    }
    private fun onlineClick(){
        debtAdapter.onItemClickOnlinePay={
            balanceViewModel.showFirstDebt=2
            balanceViewModel.getDebt=it
            findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToPaymentAznFragment(10F))
        }

    }
    private fun payAll(){
        binding.debtPayall.setOnClickListener {
            val dialogView=LayoutInflater.from(requireContext()).inflate(R.layout.alert_dialog_payment,null)
            val no=dialogView.findViewById<Button>(R.id.no)
            val yes=dialogView.findViewById<Button>(R.id.yes)

            val alertDialogBuilder= AlertDialog.Builder(requireContext())
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            no.setOnClickListener {
                if (getTotalAzn.balanceTotal<getDebtTotal.debtTotal){
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Xəta baş verdi! Zəhmət olmasa, balansınızı yoxlayın")
                }else{
                    val currentDate = LocalDateTime.now()
                    val formatter =DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                    val formatDate =currentDate.format(formatter)
                    val debtHistory=DebtHistory(formatDate,getDebtTotal.debtTotal)
                    viewModel.insertDebtHistory(debtHistory)
                    viewModel.deleteDebtAll()

                    val updateDebtTotalDouble=getDebtTotal.debtTotal-getDebtTotal.debtTotal
                    val decimalFormat3 = DecimalFormat("#.##")
                    decimalFormat3.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount3 = decimalFormat3.format(updateDebtTotalDouble).toDouble()
                    val updateDebtTotal=DebtTotal(roundedAmount3)
                    updateDebtTotal.uuid=getDebtTotal.uuid
                    viewModel.updateDebtTotal(updateDebtTotal)
                    viewModel.getDebtTotal=updateDebtTotal

                    val balanceTotalAzn=getTotalAzn.balanceTotal-getDebtTotal.debtTotal
                    val decimalFormat2 = DecimalFormat("#.##")
                    decimalFormat2.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount2 = decimalFormat2.format(balanceTotalAzn).toDouble()

                    val updateTotalAznDouble=BalanceTotalAzn(roundedAmount2)
                    updateTotalAznDouble.uuid=getTotalAzn.uuid
                    balanceViewModel.updateBalanceTotalAzn(updateTotalAznDouble)
                    balanceViewModel.updateBalanceTotalAzn(updateTotalAznDouble)

                    val insertAzn=BalanceAzn(formatDate,getDebtTotal.debtTotal,roundedAmount2)
                    balanceViewModel.insertBalanceAzn(insertAzn)
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                    findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToAdminNewsFragment("debt"))
                }

                alertDialog.dismiss()
            }
            yes.setOnClickListener {
                    balanceViewModel.showFirstDebt=3
                    balanceViewModel.getDouble=getDebtTotal.debtTotal
                    findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToPaymentAznFragment(10F))

                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
    private fun paySingle(){
        debtAdapter.onItemClickPay={
            if (getTotalAzn.balanceTotal<it.amount){
                val customToast = CustomToast(requireContext())
                customToast.showToast("Xəta baş verdi! Zəhmət olmasa, balansınızı yoxlayın")
            }else{
                val debtHistory=DebtHistory(it.history,it.amount)
                viewModel.insertDebtHistory(debtHistory)
                viewModel.deleteDebt(it)

                val updateDebtTotalDouble=getDebtTotal.debtTotal-it.amount
                val decimalFormat3 = DecimalFormat("#.##")
                decimalFormat3.roundingMode = RoundingMode.HALF_UP
                val roundedAmount3 = decimalFormat3.format(updateDebtTotalDouble).toDouble()
                val updateDebtTotal=DebtTotal(roundedAmount3)
                updateDebtTotal.uuid=getDebtTotal.uuid
                viewModel.updateDebtTotal(updateDebtTotal)

                viewModel.getDebtTotal=updateDebtTotal

                val currentDate = LocalDateTime.now()
                val formatter =DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                val formatDate =currentDate.format(formatter)

                val balanceTotalAzn=getTotalAzn.balanceTotal-it.amount
                val decimalFormat2 = DecimalFormat("#.##")
                decimalFormat2.roundingMode = RoundingMode.HALF_UP
                val roundedAmount2 = decimalFormat2.format(balanceTotalAzn).toDouble()

                val updateTotalAznDouble=BalanceTotalAzn(roundedAmount2)
                updateTotalAznDouble.uuid=getTotalAzn.uuid
                balanceViewModel.updateBalanceTotalAzn(updateTotalAznDouble)
                balanceViewModel.updateBalanceTotalAzn(updateTotalAznDouble)

                val insertAzn=BalanceAzn(formatDate,it.amount,roundedAmount2)
                balanceViewModel.insertBalanceAzn(insertAzn)
                val customToast = CustomToast(requireContext())
                customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                findNavController().navigate(DebtFragmentDirections.actionDebtFragmentToAdminNewsFragment("debt"))
            }

        }
    }
    private fun design(){
        val myColor= ContextCompat.getColor(requireContext(), R.color.primary)
        val text="Borcunuz: ${viewModel.getDebtTotal.debtTotal} AZN"
        val spannableString= SpannableString(text)
        val endLength=9
        val color= R.color.red
        spannableString.setSpan(ForegroundColorSpan(Color.BLACK),0,endLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(),color)),endLength,text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(StyleSpan(Typeface.BOLD),endLength,text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.debtDebtazn.text=spannableString

        binding.debtAzn.setOnClickListener {
            val text="Borcunuz: ${viewModel.getDebtTotal.debtTotal} AZN"
            val spannableString= SpannableString(text)
            val endLength=9
            val color= R.color.red
            spannableString.setSpan(ForegroundColorSpan(Color.BLACK),0,endLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),color)),endLength,text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),endLength,text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.debtDebtazn.text=spannableString
            binding.debtTry.setBackgroundColor(Color.WHITE)
            binding.debtTry.setTextColor(myColor)
            binding.debtAzn.setTextColor(Color.WHITE)
            binding.debtAzn.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)
            binding.recyclerviewDebt.visibility=View.VISIBLE
            if (viewModel.getDebtTotal.debtTotal!=0.0){
                binding.debtPayall.isEnabled=true
            }else{
            }
        }
        binding.debtTry.setOnClickListener {
            val text1="Borcunuz: 0.0 TRY"
            val spannableString1= SpannableString(text1)
            val endLength1=9
            val color1= R.color.red
            spannableString1.setSpan(ForegroundColorSpan(Color.BLACK),0,endLength1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString1.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),color1)),endLength1,text1.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString1.setSpan(StyleSpan(Typeface.BOLD),endLength1,text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.debtDebtazn.text=spannableString1
            binding.debtTry.setBackgroundColor(myColor)
            binding.debtTry.setTextColor(Color.WHITE)
            binding.debtAzn.setTextColor(myColor)
            binding.debtAzn.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setTextColor(myColor)
            binding.recyclerviewDebt.visibility=View.GONE
            binding.debtPayall.isEnabled=false
        }
        binding.debtHistoryUsd.setOnClickListener {
            val text1="Borcunuz: 0.0 USD"
            val spannableString1= SpannableString(text1)
            val endLength1=9
            val color1= R.color.red
            spannableString1.setSpan(ForegroundColorSpan(Color.BLACK),0,endLength1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString1.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(requireContext(),color1)),endLength1,text1.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString1.setSpan(StyleSpan(Typeface.BOLD),endLength1,text1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.debtDebtazn.text=spannableString1

            binding.debtTry.setBackgroundColor(Color.WHITE)
            binding.debtTry.setTextColor(myColor)
            binding.debtAzn.setTextColor(myColor)
            binding.debtAzn.setBackgroundColor(Color.WHITE)
            binding.debtHistoryUsd.setBackgroundColor(myColor)
            binding.debtHistoryUsd.setTextColor(Color.WHITE)
            binding.recyclerviewDebt.visibility=View.GONE
            binding.debtPayall.isEnabled=false
        }
    }
}