package com.imranmelikov.zipex.view

import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.adapter.NotificationAdapter
import com.imranmelikov.zipex.databinding.FragmentNotificationBinding
import com.imranmelikov.zipex.model.Notification
import com.imranmelikov.zipex.mvvm.NotificationViewModel
import com.imranmelikov.zipex.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationFragment @Inject constructor(
    private val notificationAdapter:NotificationAdapter
) : Fragment() {
    private lateinit var binding:FragmentNotificationBinding
    private lateinit var viewModel:NotificationViewModel

    private val swipeCallback=object :ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition
            val selectedNotification=notificationAdapter.notificationList[layoutPosition]
            viewModel.deleteNotification(selectedNotification)
            findNavController().navigate(NotificationFragmentDirections.actionNotificationFragmentToAdminNewsFragment("notification"))
        }
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val itemHeight = itemView.bottom - itemView.top
            val isCanceled = dX == 0f && !isCurrentlyActive

            if (isCanceled) {
                clearCanvas(
                    c,
                    itemView.right + dX,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                return
            }

            val background = ColorDrawable(ContextCompat.getColor(requireContext(),R.color.red2))
            background.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            background.draw(c)

            val deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.baseline_delete_forever_24)
            val intrinsicWidth = deleteIcon?.intrinsicWidth ?: 0
            val intrinsicHeight = deleteIcon?.intrinsicHeight ?: 0
            val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
            val deleteIconTop = itemView.top + deleteIconMargin
            val deleteIconBottom = deleteIconTop + intrinsicHeight
            val deleteIconLeft = itemView.right - deleteIconMargin - intrinsicWidth
            val deleteIconRight = itemView.right - deleteIconMargin
            deleteIcon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
            deleteIcon?.draw(c)

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

        private fun clearCanvas(c: Canvas, left: Float, top: Float, right: Float, bottom: Float) {
            val paint = Paint()
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            c.drawRect(left, top, right, bottom, paint)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNotificationBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(requireActivity())[NotificationViewModel::class.java]
        binding.back.setOnClickListener {
            findNavController().navigate(NotificationFragmentDirections.actionNotificationFragmentToHomeFragment())
        }
        binding.recyclerNotification.adapter=notificationAdapter
        binding.recyclerNotification.layoutManager=LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerNotification)

        viewModel.getNotifications()
        observeNotification()

        notificationAdapter.onItemClick={
            viewModel.getNotificationSingle(it.uuid!!)
        }
        observeNotificationId()
        notificationAdapter.onItemUpdate={
            val notification=Notification(it.title,it.post,"b")
            notification.uuid=it.uuid
            viewModel.updateNotification(notification)
        }
        return binding.root
    }
    private fun observeNotification(){
        viewModel.notificationMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    binding.recyclerNotification.visibility=View.VISIBLE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    it.data?.let {
                        notificationAdapter.notificationList=it
                    }
                }
                Status.LOADING->{
                    binding.recyclerNotification.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.VISIBLE
                }
                Status.ERROR->{
                    binding.recyclerNotification.visibility=View.GONE
                    binding.cryptoProgressBar.visibility=View.GONE
                    binding.cryptoErrorText.visibility=View.VISIBLE
                    Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun observeNotificationId(){
        viewModel.notificationSnl.observe(viewLifecycleOwner, Observer {
            notificationAdapter.notification=it
        })
    }
}