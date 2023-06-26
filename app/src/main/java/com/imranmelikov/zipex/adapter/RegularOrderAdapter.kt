package com.imranmelikov.zipex.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.imranmelikov.zipex.databinding.RegularOrderRowBinding
import com.imranmelikov.zipex.model.Notification
import com.imranmelikov.zipex.model.RegularOrder
import com.imranmelikov.zipex.view.WebViewActivity
import javax.inject.Inject

class RegularOrderAdapter @Inject constructor(
     private val glide:RequestManager
):RecyclerView.Adapter<RegularOrderAdapter.RegularOrderViewHolder>() {
    class RegularOrderViewHolder(var binding:RegularOrderRowBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object : DiffUtil.ItemCallback<RegularOrder>(){
        override fun areItemsTheSame(oldItem: RegularOrder, newItem: RegularOrder): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: RegularOrder, newItem: RegularOrder): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer= AsyncListDiffer(this,diffUtil)
    var regularOrderList:List<RegularOrder>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegularOrderViewHolder {
        val binding=RegularOrderRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RegularOrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return regularOrderList.size
    }

    override fun onBindViewHolder(holder: RegularOrderViewHolder, position: Int) {
        val regularOrderArraylist=regularOrderList.get(position)
        glide.load(regularOrderArraylist.imgUrl)
            .into(holder.binding.regularImage)

        holder.itemView.setOnClickListener {
            val url = regularOrderArraylist.url
            val intent = Intent(holder.itemView.context, WebViewActivity::class.java)
            intent.putExtra("key",url)
            holder.itemView.context.startActivity(intent)
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            holder.itemView.context.startActivity(intent)
        }
    }
}