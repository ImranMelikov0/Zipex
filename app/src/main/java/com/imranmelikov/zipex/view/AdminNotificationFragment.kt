package com.imranmelikov.zipex.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.FragmentAdminNewsBinding
import com.imranmelikov.zipex.databinding.FragmentAdminNotificationBinding
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.mvvm.NotificationViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminNotificationFragment : Fragment() {
    private lateinit var binding: FragmentAdminNotificationBinding
    private lateinit var viewModel:NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAdminNotificationBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[NotificationViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(AdminNotificationFragmentDirections.actionAdminNotificationFragment2ToAdminFragment())
        }
        binding.button2.setOnClickListener {
            viewModel.makeNotification(binding.editTextText.text.toString()
                ,binding.editTextText2.text.toString())
        }
        observeNotification()
        return binding.root
    }
    private fun observeNotification(){
        viewModel.notificationErrorMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR->{
                    val customToast = CustomToast(requireContext())
                    customToast.showToast(it.message?:"Enter Values")
                }
                Status.LOADING->{

                }
                Status.SUCCESS->{
                    val customToast = CustomToast(requireContext())
                    customToast.showToast("Bildiriş əlavə olundu")
                    it.data?.let {

                        val channelId = "my_channel_id"
                        val channelName = "My Channel"
                        val importance = NotificationManager.IMPORTANCE_DEFAULT
                        val channel = NotificationChannel(channelId, channelName, importance)

                        val notificationId = it.uuid
                        val contentTitle = it.title
                        val contentText = it.post
                        val notificationIcon = R.drawable.vipwx

                        val notificationBuilder =
                            NotificationCompat.Builder(requireContext(), channelId)
                                .setSmallIcon(notificationIcon)
                                .setContentTitle(contentTitle)
                                .setContentText(contentText)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                        try {
                            val notificationManager =
                                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                val permission = NotificationManagerCompat.IMPORTANCE_DEFAULT
                                val granted =
                                    notificationManager.areNotificationsEnabled() && notificationManager.getImportance() >= permission
                                if (granted) {
                                    notificationManager.createNotificationChannel(channel)
                                    notificationManager.notify(
                                        notificationId?:1,
                                        notificationBuilder.build()
                                    )
                                } else {
                                }
                            } else {
                                notificationManager.notify(
                                    notificationId?:1,
                                    notificationBuilder.build()
                                )
                            }
                        } catch (e: SecurityException) {
                            e.printStackTrace()

                        }
                    }

                    viewModel.resetNotificationErrorMessage()
                    findNavController().popBackStack()
                }
            }
        })
    }
}