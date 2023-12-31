package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.AdminAdapter
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.databinding.FragmentCartBinding
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
import dagger.hilt.android.AndroidEntryPoint
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment @Inject constructor(
    private val cartAdapter: CartAdapter
) : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel:CartViewModel
    private lateinit var balanceViewModel:BalanceViewModel
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCartBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(requireActivity())[CartViewModel::class.java]
        balanceViewModel=ViewModelProvider(requireActivity())[BalanceViewModel::class.java]
        adminViewModel=ViewModelProvider(requireActivity()).get(AdminViewModel::class.java)
        binding.button.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment())
        }
        binding.back.setOnClickListener {
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToHomeFragment())
        }
        binding.addrecyclerview.layoutManager=LinearLayoutManager(requireContext())
        binding.addrecyclerview.adapter=cartAdapter
        viewModel.getCarts()
        observeCart()
        onItemClick()
        balanceViewModel.getTotalBalanceTry()
        balanceViewModel.getTotalBalanceUsd()
        observeBalance()
        observeBalanceUsd()

        cartAdapter.onItemClickUpdateSigorta={
            viewModel.updateCart(it)
            val adminLink=AdminLink(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment)
            adminLink.uuid=it.uuid
            adminViewModel.updateAdminLink(adminLink)
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToPaymentFragment(9F))
        }
    }
private fun onItemClick(){
    cartAdapter.onItemClickCartUpdate={
        viewModel.updateCart(it)
        updateCart()
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment("String"))
    }
    cartAdapter.onItemClickDelete={
        viewModel.deleteCart(it)
        findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment("String"))
    }
    cartAdapter.onItemClickOnlinePay={
        balanceViewModel.showFirst=false
        balanceViewModel.getDouble=it.price
        balanceViewModel.getLink=it
    }
    cartAdapter.onItemClickOnlinePayUsd={
        balanceViewModel.showFirst=false
        balanceViewModel.getDouble=it.price
        balanceViewModel.getLink=it
    }
}
    private fun observeCart(){
        viewModel.cartMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.addrecyclerview.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.textView.visibility=View.GONE
                    binding.button.visibility=View.GONE
                    it.data?.let {
                        cartAdapter.cartList=it
                        if (it.isEmpty()){
                            binding.addrecyclerview.visibility=View.GONE
                            binding.textView.visibility=View.VISIBLE
                            binding.button.visibility=View.VISIBLE
                        }else{
                            binding.swipeRefreshLayout.setOnRefreshListener {
                                binding.addrecyclerview.visibility=View.GONE
                                binding.cryptoErrorText.visibility=View.GONE
                                binding.cryptoProgressBar.visibility=View.VISIBLE
                                viewModel.getCarts()
                                balanceViewModel.getTotalBalanceTry()
                                balanceViewModel.getTotalBalanceUsd()
                                binding.swipeRefreshLayout.isRefreshing=false
                            }
                        }
                        it.map {
                            val currentDate= LocalDateTime.now()
                            val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                            val formatDate=currentDate.format(formatter)
                           viewModel.refreshData(it.history,formatDate,it)
                        }
                    }
                }
                Status.LOADING->{
                    binding.addrecyclerview.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                    binding.textView.visibility=View.GONE
                    binding.button.visibility=View.GONE
                }
                Status.ERROR->{
                    binding.addrecyclerview.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.button.visibility=View.VISIBLE
                    binding.textView.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun updateCart(){
        viewModel.updateLink.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                }
                Status.LOADING->{
                    binding.addrecyclerview.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                    binding.textView.visibility=View.GONE
                    binding.button.visibility=View.GONE
                }
                Status.SUCCESS->{
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Məlumat əlavə olundu")
                    binding.addrecyclerview.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.textView.visibility=View.GONE
                    binding.button.visibility=View.GONE
                }
            }
        })
    }
    private fun observeBalance(){
                    balanceViewModel.balanceTotalTryLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceTotalTry->
                                    resourceBalanceTotalTry.data?.let {balanceTotalTry->
                                                cartAdapter.onItemClickBalancePay={
                                                    if ( balanceTotalTry.balanceTotal<it.price){
                                                        val customToast = CustomToast(requireContext())
                                                        customToast.showToast("Xəta baş verdi! Zəhmət olmasa, balansınızı yoxlayın")
                                                    }else{
                                                        val payment= balanceTotalTry.balanceTotal-it.price
                                                        val decimalFormat = DecimalFormat("#.##")
                                                        decimalFormat.roundingMode = RoundingMode.HALF_UP
                                                        val roundedAmount = decimalFormat.format(payment).toDouble()
                                                        val updateTotalTry= BalanceTotalTry(roundedAmount)
                                                        updateTotalTry.uuid=balanceTotalTry.uuid
                                                        balanceViewModel.updateBalanceTotalTry(updateTotalTry)
                                                        balanceViewModel.getBalanceTotalTry=updateTotalTry
                                                        val currentDate= LocalDateTime.now()
                                                        val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                                                        val formatDate=currentDate.format(formatter)
                                                        val insertBalanceTry= BalanceTry(formatDate,it.price,roundedAmount)
                                                        balanceViewModel.insertBalanceTry(insertBalanceTry)

                                                        val updateCart=Link(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,"Ödənilib")
                                                        updateCart.uuid=it.uuid
                                                        viewModel.updateCart(updateCart)

                                                        val adminLink=AdminLink(updateCart.url,updateCart.category,updateCart.count,updateCart.color,
                                                        updateCart.size,updateCart.price,updateCart.comment,updateCart.history,updateCart.country,updateCart.sigorta,updateCart.payment)
                                                       adminLink.uuid=updateCart.uuid
                                                        adminViewModel.insertAdminLink(adminLink)

                                                        val customToast = CustomToast(requireContext())
                                                        customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                                                        findNavController().navigate(CartFragmentDirections.actionCartFragmentToPaymentFragment(4F))
                                                    }
                                                }
                                    }
                    })
    }
    private fun observeBalanceUsd(){
        balanceViewModel.balanceTotalUsdLiveData.observe(viewLifecycleOwner, Observer {resourceBalanceTotalUsd->
            resourceBalanceTotalUsd.data?.let {balanceTotalUsd->
                cartAdapter.onItemClickBalancePayUsd={
                    if ( balanceTotalUsd.balanceTotal<it.price){
                        val customToast = CustomToast(requireContext())
                        customToast.showToast("Xəta baş verdi! Zəhmət olmasa, balansınızı yoxlayın")
                    }else{
                        val payment= balanceTotalUsd.balanceTotal-it.price
                        val decimalFormat = DecimalFormat("#.##")
                        decimalFormat.roundingMode = RoundingMode.HALF_UP
                        val roundedAmount = decimalFormat.format(payment).toDouble()
                        val updateTotalUsd= BalanceTotalUsd(roundedAmount)
                        updateTotalUsd.uuid=balanceTotalUsd.uuid
                        balanceViewModel.updateBalanceTotalUsd(updateTotalUsd)
                        balanceViewModel.getBalanceTotalUsd=updateTotalUsd
                        val currentDate= LocalDateTime.now()
                        val formatter= DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                        val formatDate=currentDate.format(formatter)
                        val insertBalanceUsd= BalanceUsd(formatDate,it.price,roundedAmount)
                        balanceViewModel.insertBalanceUsd(insertBalanceUsd)

                        val updateCart=Link(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,"Ödənilib")
                        updateCart.uuid=it.uuid
                        viewModel.updateCart(updateCart)

                        val adminLink=AdminLink(updateCart.url,updateCart.category,updateCart.count,updateCart.color,
                            updateCart.size,updateCart.price,updateCart.comment,updateCart.history,updateCart.country,updateCart.sigorta,updateCart.payment)
                        adminLink.uuid=updateCart.uuid
                        adminViewModel.insertAdminLink(adminLink)

                        val customToast = CustomToast(requireContext())
                        customToast.showToast("Əməliyyat uğurla yerinə yetirildi")
                        findNavController().navigate(CartFragmentDirections.actionCartFragmentToPaymentFragment(4F))
                    }
                }
            }
        })
    }
}