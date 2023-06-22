package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.OrderAdapter
import com.imranmelikov.zipex.databinding.FragmentOrderBinding
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.mvvm.Order1ViewModel
import com.imranmelikov.zipex.mvvm.Order2ViewModel
import com.imranmelikov.zipex.mvvm.Order3ViewModel
import com.imranmelikov.zipex.mvvm.Order4ViewModel
import com.imranmelikov.zipex.mvvm.Order5ViewModel
import com.imranmelikov.zipex.mvvm.Order6ViewModel
import com.imranmelikov.zipex.mvvm.Order7ViewModel
import com.imranmelikov.zipex.mvvm.Order8ViewModel
import com.imranmelikov.zipex.mvvm.Order9ViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OrderFragment @Inject constructor(
   private val orderAdapter: OrderAdapter
) : Fragment() {
private lateinit var binding:FragmentOrderBinding
private lateinit var viewModel:Order1ViewModel
    private lateinit var viewModel2:Order2ViewModel
    private lateinit var viewModel3: Order3ViewModel
    private lateinit var viewModel4: Order4ViewModel
    private lateinit var viewModel5: Order5ViewModel
    private lateinit var viewModel6: Order6ViewModel
    private lateinit var viewModel7: Order7ViewModel
    private lateinit var viewModel8: Order8ViewModel
    private lateinit var viewModel9: Order9ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentOrderBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[Order1ViewModel::class.java]
        viewModel2=ViewModelProvider(requireActivity())[Order2ViewModel::class.java]
        viewModel3=ViewModelProvider(requireActivity())[Order3ViewModel::class.java]
        viewModel4=ViewModelProvider(requireActivity())[Order4ViewModel::class.java]
        viewModel5=ViewModelProvider(requireActivity())[Order5ViewModel::class.java]
        viewModel6=ViewModelProvider(requireActivity())[Order6ViewModel::class.java]
        viewModel7=ViewModelProvider(requireActivity())[Order7ViewModel::class.java]
        viewModel8=ViewModelProvider(requireActivity())[Order8ViewModel::class.java]
        viewModel9=ViewModelProvider(requireActivity())[Order9ViewModel::class.java]
      binding.back.setOnClickListener {
          findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToHomeFragment())
      }

        binding.orderRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.orderRecyclerView.adapter=orderAdapter

        viewModel.getOrder1()
        viewModel2.getOrder2()
        viewModel3.getOrder3()
        viewModel4.getOrder4()
        viewModel5.getOrder5()
        viewModel6.getOrder6()
        viewModel7.getOrder7()
        viewModel8.getOrder8()
        viewModel9.getOrder9()
        observeOrder1()
        observeOrder2()
        observeOrder3()
        observeOrder4()
        observeOrder5()
        observeOrder6()
        observeOrder7()
        observeOrder8()
        observeOrder9()

        orderAdapter.onItemClickOrder1={
            val order1=Order1(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office)
            order1.uuid=it.uuid
            viewModel.updateOrder1(order1)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
        }

        orderAdapter.onItemClickOrder2={
            val order2=Order2(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office)
            order2.uuid=it.uuid
            viewModel2.updateOrder2(order2)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
        }

        orderAdapter.onItemClickOrder3={
            val order3= Order3(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office)
            order3.uuid=it.uuid
            viewModel3.updateOrder3(order3)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
        }
        return binding.root
    }

    private fun observeOrder1(){
        viewModel.order1Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order1List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder2(){
        viewModel2.order2Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order2List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder3(){
        viewModel3.order3Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order3List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder4(){
        viewModel4.order4Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order4List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder5(){
        viewModel5.order5Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order5List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder6(){
        viewModel6.order6Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order6List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder7(){
        viewModel7.order7Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order7List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder8(){
        viewModel8.order8Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order8List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
    private fun observeOrder9(){
        viewModel9.order9Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.orderRecyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        orderAdapter.order9List=it
                    }

                }
                Status.ERROR->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.orderRecyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
}