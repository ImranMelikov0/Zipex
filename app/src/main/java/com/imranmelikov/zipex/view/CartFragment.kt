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
import androidx.recyclerview.widget.LinearLayoutManager
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.CartAdapter
import com.imranmelikov.zipex.databinding.FragmentCartBinding
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import com.imranmelikov.zipex.mvvm.CartViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment @Inject constructor(
    private val cartAdapter: CartAdapter
) : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel:CartViewModel

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
        cartAdapter.onItemClickCartUpdate={
            viewModel.updateCart(it)
            updateCart()
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment("String"))
        }
        cartAdapter.onItemClickDelete={
            viewModel.deleteCart(it)
            findNavController().navigate(CartFragmentDirections.actionCartFragmentToLinkFragment("String"))
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
                    Toast.makeText(requireContext(),"Məlumat əlavə olundu",Toast.LENGTH_SHORT).show()
                    binding.addrecyclerview.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.textView.visibility=View.GONE
                    binding.button.visibility=View.GONE
                }
            }
        })
    }
}