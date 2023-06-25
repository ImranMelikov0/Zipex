package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.NotificationRowBinding
import com.imranmelikov.zipex.model.Notification
import com.imranmelikov.zipex.mvvm.NotificationViewModel
import javax.inject.Inject

class NotificationAdapter @Inject constructor(

):RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    class NotificationViewHolder(var binding:NotificationRowBinding):RecyclerView.ViewHolder(binding.root)

   var onItemClick:((Notification)->Unit)?=null
     var  notification:Notification?=null
    var onItemUpdate:((Notification)->Unit)?=null

   private val diffUtil=object :DiffUtil.ItemCallback<Notification>(){
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem==newItem
        }

    }
   private val recyclerDiffer=AsyncListDiffer(this,diffUtil)
    var notificationList:List<Notification>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding=NotificationRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotificationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notificationArrayList=notificationList.get(position)
        holder.binding.notificationTitletext.text=notificationArrayList.title
        holder.binding.notificationText.text=notificationArrayList.post

        if (notificationArrayList.titleGray=="b"){
            holder.binding.notificationImage.setImageResource(R.drawable.baseline_notification1)
            holder.binding.notificationTitletext.setTypeface(null,Typeface.NORMAL)
            holder.binding.notificationTitletext.setTextColor(Color.GRAY)
            holder.binding.notificationText.setTextColor(Color.GRAY)
        }else{

        }
        holder.itemView.setOnClickListener {
            onItemUpdate?.let {
                it(notificationArrayList)
            }
            onItemClick?.let {
                val dialogview=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_notification,null)
                val titletextview=dialogview.findViewById<TextView>(R.id.title_notification_dialog)
                val posttextview=dialogview.findViewById<TextView>(R.id.post_notification_dialog)
                val button=dialogview.findViewById<Button>(R.id.notification_dialog_button)

                notification=notificationArrayList

                titletextview.text=notification?.title
                posttextview.text= notification?.post


                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogview)

                val alertDialog=alertDialogBuilder.create()

                button.setOnClickListener {
                    holder.binding.notificationImage.setImageResource(R.drawable.baseline_notification1)
                    holder.binding.notificationTitletext.setTypeface(null,Typeface.NORMAL)
                    holder.binding.notificationTitletext.setTextColor(Color.GRAY)
                    holder.binding.notificationText.setTextColor(Color.GRAY)
                    alertDialog.dismiss()
                }

                alertDialog.show()
                it(notificationArrayList)
            }


        }
    }

}