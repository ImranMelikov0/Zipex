package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.AdminrecyclerRowBinding
import com.imranmelikov.zipex.model.AdminLink
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.view.AdminFragmentDirections
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AdminAdapter @Inject constructor():RecyclerView.Adapter<AdminAdapter.AdminViewHolder>() {
    class AdminViewHolder(var binding:AdminrecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    var onItemClickConfirm:((Order1)->Unit)?=null
    var onItemClickReject:((AdminLink)->Unit)?=null
    var onItemClickConfirmDelete:((AdminLink)->Unit)?=null
    private val diffUtil=object : DiffUtil.ItemCallback<AdminLink>(){
        override fun areItemsTheSame(oldItem: AdminLink, newItem: AdminLink): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: AdminLink, newItem: AdminLink): Boolean {
            return oldItem==newItem
        }
    }
    private val recyclerDiffer= AsyncListDiffer(this,diffUtil)

    var AdminList:List<AdminLink>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val binding=AdminrecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return AdminList.size
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val adminList=AdminList.get(position)
        holder.binding.cartIdtitle.text=adminList.url
        holder.binding.cartHistory.text=adminList.history
        holder.binding.cartSize.text=adminList.size
        holder.binding.cartColor.text=adminList.color
        holder.binding.cartPrice.text=adminList.price.toString()
        holder.binding.cartQuantity.text=adminList.count.toString()
        holder.binding.cartComment.text=adminList.comment

        holder.binding.buttonconfirm.setOnClickListener {
            if (holder.binding.magazaname.text.toString().isEmpty()||holder.binding.magazacode.text.toString().isEmpty()){
                val customToast = CustomToast(holder.itemView.context)
                customToast.showToast("Məlumatları qeyd edin")
            }else{
                val currentDate = LocalDateTime.now()
                val formatter =
                    DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                val formatDate = currentDate.format(formatter)
                val order1=Order1(adminList.url,adminList.category,adminList.count,adminList.color,adminList.size
                ,adminList.price,adminList.comment,formatDate,adminList.country,adminList.sigorta,adminList.payment,holder.binding.magazaname.text.toString(),holder.binding.magazacode.text.toString(),"Azərbaycanda hər hansısa bir yer")
                onItemClickConfirm?.let {
                    it(order1)
                }
                onItemClickConfirmDelete?.let {
                    it(adminList)
                }
                Navigation.findNavController(it).navigate(AdminFragmentDirections.actionAdminFragmentToAdminNewsFragment("string"))
                val customToast = CustomToast(holder.itemView.context)
                customToast.showToast("Bağlama təsdiq olundu")
            }
        }
        holder.binding.buttonreject.setOnClickListener {
            onItemClickReject?.let {
                it(adminList)
            }
            Navigation.findNavController(it).navigate(AdminFragmentDirections.actionAdminFragmentToAdminNewsFragment("string"))
            val customToast = CustomToast(holder.itemView.context)
            customToast.showToast("Bağlama silindi")
        }

    }
}