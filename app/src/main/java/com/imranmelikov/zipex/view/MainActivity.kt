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
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var navView:NavigationView?=null

    @Inject
    lateinit var fragmentFactory: ZipexFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory=fragmentFactory
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        navView=binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val bottomNav=binding.appBarMain.bottomNavigationView2
        bottomNav.setupWithNavController(navController)
        navView!!.setupWithNavController(navController)

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


    }