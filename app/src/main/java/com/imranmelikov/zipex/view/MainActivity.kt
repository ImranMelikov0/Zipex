package com.imranmelikov.zipex.view

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.ActivityMainBinding
import com.imranmelikov.zipex.mvvm.BalanceViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var navView:NavigationView?=null
    private lateinit var balanceViewModel:BalanceViewModel

    @Inject
    lateinit var fragmentFactory: ZipexFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory=fragmentFactory
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        balanceViewModel=ViewModelProvider(this)[BalanceViewModel::class.java]
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        navView=binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val bottomNav=binding.appBarMain.bottomNavigationView2
        bottomNav.setupWithNavController(navController)
        navView!!.setupWithNavController(navController)
        balanceViewModel.getTotalBalanceAzn()
        balanceViewModel.getTotalBalanceTry()
        balanceViewModel.getTotalBalanceUsd()
        observeBalanceTotalAzn()
        observeBalanceTotalTry()
        observeBalanceTotalUsd()

    }
    fun handleButtonClick(){
        val drawerLayout=binding.drawerLayout
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
       if (binding.drawerLayout.isOpen){
           binding.drawerLayout.closeDrawer(GravityCompat.START)
           }else{
           super.onBackPressed()
       }
    }
    private fun observeBalanceTotalTry(){
        balanceViewModel.balanceTotalTryLiveData.observe(this, Observer {
            it.data?.let {
                balanceViewModel.getBalanceTotalTry=it
                navView=binding.navView
                val headerView:View=navView!!.getHeaderView(0)
                val textView1: TextView = headerView.findViewById(R.id.textview1)
                textView1.text="Sifariş balansı : ${it.balanceTotal} TRY"
            }
        })
    }
    private fun observeBalanceTotalAzn(){
        balanceViewModel.balanceTotalAznLiveData.observe(this, Observer {
            it.data?.let {
                balanceViewModel.getBalanceTotalAzn=it
                navView=binding.navView
                val headerView:View=navView!!.getHeaderView(0)
                val textView2: TextView = headerView.findViewById(R.id.textview2)
                textView2.text="Servis balansı : ${it.balanceTotal} AZN"
            }
        })
    }
    private fun observeBalanceTotalUsd(){
        balanceViewModel.balanceTotalUsdLiveData.observe(this, Observer {
            it.data?.let {
                balanceViewModel.getBalanceTotalUsd=it
                navView=binding.navView
                val headerView:View=navView!!.getHeaderView(0)
                val textView3: TextView = headerView.findViewById(R.id.textview3)
                textView3.text="USD balansı : ${it.balanceTotal} USD"
            }
        })
    }

    }