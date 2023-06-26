package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.orderRecyclerView.visibility=View.GONE
            binding.cryptoErrorText.visibility=View.GONE
            binding.cryptoProgressBar.visibility=View.VISIBLE
            viewModel.getOrder1()
            viewModel2.getOrder2()
            viewModel3.getOrder3()
            viewModel4.getOrder4()
            viewModel5.getOrder5()
            viewModel6.getOrder6()
            viewModel7.getOrder7()
            viewModel8.getOrder8()
            viewModel9.getOrder9()
            binding.swipeRefreshLayout.isRefreshing=false
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
        orderButtons()

        orderAdapter.onItemClickOrder1={
//            viewModel.order1=it
//            viewModel.onItemString="b"
            val order1=Order1(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office)
            order1.uuid=it.uuid
            viewModel.updateOrder1(order1)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update1"))
        }
//        if (viewModel.onItemString=="b"){
//            val order1=Order1(viewModel.order1.url,viewModel.order1.category,viewModel.order1.count,viewModel.order1.color,viewModel.order1.size,viewModel.order1.price,viewModel.order1.comment,viewModel.order1.history,viewModel.order1.country,"Sığortalanıb",viewModel.order1.payment,viewModel.order1.marketName,viewModel.order1.marketCode,viewModel.order1.office)
//            order1.uuid=viewModel.order1.uuid
//            viewModel.updateOrder1(order1)
//            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
//            viewModel.onItemString="a"
//        }else{
//
//        }

        orderAdapter.onItemClickOrder2={
//            viewModel2.order2=it
//            viewModel.onItemString="c"
            val order2=Order2(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office)
            order2.uuid=it.uuid
            viewModel2.updateOrder2(order2)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update2"))

        }
//        if (viewModel.onItemString=="c"){
//            val order2=Order2(viewModel2.order2.url,viewModel2.order2.category,viewModel2.order2.count,viewModel2.order2.color,viewModel2.order2.size,viewModel2.order2.price,viewModel2.order2.comment,viewModel2.order2.history,viewModel2.order2.country,"Sığortalanıb",viewModel2.order2.payment,viewModel2.order2.marketName,viewModel2.order2.marketCode,viewModel2.order2.office)
//            order2.uuid=viewModel2.order2.uuid
//            viewModel2.updateOrder2(order2)
//            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
//            viewModel.onItemString="a"
//        }else{
//
//        }

        orderAdapter.onItemClickOrder3={
//            viewModel3.order3=it
//            viewModel.onItemString="d"
            val order3=Order3(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,"Sığortalanıb",it.payment,it.marketName,it.marketCode,it.office,it.delivery,it.weight)
            order3.uuid=it.uuid
            viewModel3.updateOrder3(order3)
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update3"))

        }
//        if (viewModel.onItemString=="d"){
//            val order3=Order3(viewModel3.order3.url,viewModel3.order3.category,viewModel3.order3.count,viewModel3.order3.color,viewModel3.order3.size,viewModel3.order3.price,viewModel3.order3.comment,viewModel3.order3.history,viewModel3.order3.country,"Sığortalanıb",viewModel3.order3.payment,viewModel3.order3.marketName,viewModel3.order3.marketCode,viewModel3.order3.office,viewModel3.order3.delivery,viewModel3.order3.weight)
//            order3.uuid=viewModel3.order3.uuid
//            viewModel3.updateOrder3(order3)
//            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("update"))
//            viewModel.onItemString="a"
//        }else{
//
//        }
        return binding.root
    }

    private fun orderButtons(){
        val myColor= ContextCompat.getColor(requireContext() , R.color.primary)
       binding.orderSifaris.setOnClickListener {
            orderAdapter.showFirst=1
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order1"))
        }
        binding.orderAnbar.setOnClickListener {
           orderAdapter.showFirst=2
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order2"))
        }
        binding.orderSmartbeyangozleyir.setOnClickListener {
           orderAdapter.showFirst=3
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order3"))
        }
        binding.orderSmartbeyanedilib.setOnClickListener {
           orderAdapter.showFirst=4
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order4"))
        }
        binding.orderYol.setOnClickListener {
           orderAdapter.showFirst=5
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order5"))
        }
        binding.orderGomruk.setOnClickListener {
           orderAdapter.showFirst=6
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order6"))
        }
        binding.orderCesidleme.setOnClickListener {
           orderAdapter.showFirst=7
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order7"))
        }
       binding.orderCatib.setOnClickListener {
           orderAdapter.showFirst=8
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order8"))
        }
        binding.orderTehvil.setOnClickListener {
           orderAdapter.showFirst=9
            Navigation.findNavController(it).navigate(OrderFragmentDirections.actionOrderFragmentToAdminNewsFragment("order9"))
        }
        if (orderAdapter.showFirst==1){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if(orderAdapter.showFirst==2){
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderSifaris.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if (orderAdapter.showFirst==3){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)

        }else if(orderAdapter.showFirst==4){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)

        }else if (orderAdapter.showFirst==5){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if (orderAdapter.showFirst==6){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if (orderAdapter.showFirst==7){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if (orderAdapter.showFirst==8){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)
            binding.orderTehvil.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
            binding.orderTehvil.setTextColor(myColor)
        }else if (orderAdapter.showFirst==9){
            binding.orderAnbar.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyanedilib.setBackgroundColor(Color.WHITE)
            binding.orderSmartbeyangozleyir.setBackgroundColor(Color.WHITE)
            binding.orderYol.setBackgroundColor(Color.WHITE)
            binding.orderGomruk.setBackgroundColor(Color.WHITE)
            binding.orderCesidleme.setBackgroundColor(Color.WHITE)
            binding.orderCatib.setBackgroundColor(Color.WHITE)
            binding.orderSifaris.setBackgroundColor(Color.WHITE)

            binding.orderAnbar.setTextColor(myColor)
            binding.orderSmartbeyanedilib.setTextColor(myColor)
            binding.orderSmartbeyangozleyir.setTextColor(myColor)
            binding.orderYol.setTextColor(myColor)
            binding.orderGomruk.setTextColor(myColor)
            binding.orderCesidleme.setTextColor(myColor)
            binding.orderCatib.setTextColor(myColor)
            binding.orderSifaris.setTextColor(myColor)
        }
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