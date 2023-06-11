package com.imranmelikov.zipex.view

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.newsAdapter
import com.imranmelikov.zipex.databinding.FragmentHomeBinding
import com.imranmelikov.zipex.mvvm.NewsViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
    private val newsAdapter: newsAdapter
) : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var newsViewModel:NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        newsViewModel=ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        binding.homeMenu.setOnClickListener {
            (activity as? MainActivity)?.handleButtonClick()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeNotification.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNotificationFragment())
        }
        binding.homeBonus.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBonusFragment())
        }
        binding.homeRegularorder.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegularOrderFragment())
        }
        binding.homeLink.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLinkFragment())
        }
        binding.homeBalance.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBalanceFragment())
        }
        binding.homeOrder.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOrderFragment())
        }
        binding.homeLocation.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddressFragment())
        }
        binding.homeTariff.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTariffFragment())
        }
        binding.homeCourier.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCourierFragment())
        }
        binding.homeService.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToServiceFragment())
        }
        binding.homeContact.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToContactFragment())
        }

        binding.homeRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        newsViewModel.getNews()
        observeNews()
    }

    private fun observeNews(){
        newsViewModel.newsMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.homeRecyclerView.visibility=View.VISIBLE
                    it.data?.let {
                        newsAdapter.newsList=it
                        binding.homeRecyclerView.adapter=newsAdapter
                    }
                    newsViewModel.resetNewsMessage()
                }
                Status.LOADING->{
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.homeRecyclerView.visibility=View.GONE
                }
                Status.ERROR->{
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.homeRecyclerView.visibility=View.GONE
                }
            }
        })
    }



}