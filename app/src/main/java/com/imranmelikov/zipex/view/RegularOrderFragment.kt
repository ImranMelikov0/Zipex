package com.imranmelikov.zipex.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.RegularOrderAdapter
import com.imranmelikov.zipex.databinding.FragmentOrderBinding
import com.imranmelikov.zipex.databinding.FragmentRegularOrderBinding
import com.imranmelikov.zipex.model.RegularOrder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegularOrderFragment @Inject constructor(
   private var regularOrderAdapter: RegularOrderAdapter
) : Fragment() {
private lateinit var binding:FragmentRegularOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegularOrderBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            findNavController().navigate(RegularOrderFragmentDirections.actionRegularOrderFragmentToHomeFragment())
        }
        binding.regularRecyclerview.adapter=regularOrderAdapter
        binding.regularRecyclerview.layoutManager=GridLayoutManager(requireContext(),2)

        val regularOrder1=RegularOrder("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRODugclY3VcoiUgyAa6xsmXGQkwQJzBOZMKw&usqp=CAU","https://images.app.goo.gl/QByowVjxVzrNHy7P7",1)
        val regularOrder2=RegularOrder("https://images.app.goo.gl/QByowVjxVzrNHy7P7","https://images.app.goo.gl/QByowVjxVzrNHy7P7",2)
        val regularOrderList= listOf(regularOrder1,regularOrder2)
        regularOrderAdapter.regularOrderList=regularOrderList
        binding.cryptoProgressBar.visibility=View.GONE
        return binding.root
    }
}