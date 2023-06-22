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
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.AdminDetailAdapter
import com.imranmelikov.zipex.adapter.OrderAdapter
import com.imranmelikov.zipex.databinding.FragmentAdminDetailBinding
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order5
import com.imranmelikov.zipex.model.Order6
import com.imranmelikov.zipex.model.Order7
import com.imranmelikov.zipex.model.Order8
import com.imranmelikov.zipex.model.Order9
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
class AdminDetailFragment @Inject constructor(
    private val adminDetailAdapter: AdminDetailAdapter
) : Fragment() {
    private lateinit var binding: FragmentAdminDetailBinding
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
        binding= FragmentAdminDetailBinding.inflate(inflater,container,false)
        viewModel= ViewModelProvider(requireActivity())[Order1ViewModel::class.java]
        viewModel2= ViewModelProvider(requireActivity())[Order2ViewModel::class.java]
        viewModel3= ViewModelProvider(requireActivity())[Order3ViewModel::class.java]
        viewModel4= ViewModelProvider(requireActivity())[Order4ViewModel::class.java]
        viewModel5= ViewModelProvider(requireActivity())[Order5ViewModel::class.java]
        viewModel6= ViewModelProvider(requireActivity())[Order6ViewModel::class.java]
        viewModel7= ViewModelProvider(requireActivity())[Order7ViewModel::class.java]
        viewModel8= ViewModelProvider(requireActivity())[Order8ViewModel::class.java]
        viewModel9= ViewModelProvider(requireActivity())[Order9ViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminFragment())
        }
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adminDetailAdapter

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

        adminDetailAdapter.onItemClickOrder2Add= {
        viewModel.deleteOrder1(it)
            val order2=Order2(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order2.uuid=it.uuid
        viewModel2.insertOrder2(order2)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrderDelete={
            viewModel.deleteOrder1(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder3Add={
            viewModel2.deleteOrder2(it)
            val order3=Order3(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order3.uuid=it.uuid
            viewModel3.insertOrder3(order3)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder2Delete={
            viewModel2.deleteOrder2(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder4Add={
            viewModel3.deleteOrder3(it)
            val order4=Order4(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
           order4.uuid=it.uuid
            viewModel4.insertOrder4(order4)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder3Delete={
            viewModel3.deleteOrder3(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
       adminDetailAdapter.onItemClickOrder5Add={
           viewModel4.deleteOrder4(it)
           val order5=Order5(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office,0,0)
           order5.uuid=it.uuid
           viewModel5.insertOrder5(order5)
           findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
       }
        adminDetailAdapter.onItemClickOrder4Delete={
            viewModel4.deleteOrder4(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder6Add={
            viewModel5.deleteOrder5(it)
            val order6=Order6(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order6.uuid=it.uuid
            viewModel6.insertOrder6(order6)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder5Delete={
            viewModel5.deleteOrder5(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder7Add={
            viewModel6.deleteOrder6(it)
            val order7=Order7(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order7.uuid=it.uuid
            viewModel7.insertOrder7(order7)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder6Delete={
            viewModel6.deleteOrder6(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder8Add={
            viewModel7.deleteOrder7(it)
            val order8=Order8(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order8.uuid=it.uuid
            viewModel8.insertOrder8(order8)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder7Delete={
            viewModel7.deleteOrder7(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder9Add={
            viewModel8.deleteOrder8(it)
            val order9=Order9(it.url,it.category,it.count,it.color,it.size,it.price,it.comment,it.history,it.country,it.sigorta,it.payment,it.marketName,it.marketCode,it.office)
            order9.uuid=it.uuid
            viewModel9.insertOrder9(order9)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        adminDetailAdapter.onItemClickOrder8Delete={
            viewModel8.deleteOrder8(it)
            findNavController().navigate(AdminDetailFragmentDirections.actionAdminDetailFragmentToAdminNewsFragment("order1"))
        }
        return binding.root
    }
    private fun observeOrder1(){
        viewModel.order1Msg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order1List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                       adminDetailAdapter.order2List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order3List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order4List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order5List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order6List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order7List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order8List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
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
                    binding.recyclerView.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        adminDetailAdapter.order9List=it
                    }

                }
                Status.ERROR->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                }
                Status.LOADING->{
                    binding.recyclerView.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
            }
        })
    }
}