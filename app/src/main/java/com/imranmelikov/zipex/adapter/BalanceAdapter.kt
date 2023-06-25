package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.BalanceRowBinding
import com.imranmelikov.zipex.model.BalanceAzn
import com.imranmelikov.zipex.model.BalanceTotalAzn
import com.imranmelikov.zipex.model.BalanceTotalTry
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.view.BalanceFragmentDirections
import javax.inject.Inject

class BalanceAdapter @Inject constructor():RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder>() {
    class BalanceViewHolder(var binding:BalanceRowBinding):RecyclerView.ViewHolder(binding.root)

//   var balanceList2=BalanceTotalTry(0.0)
//    var balanceList3= BalanceTotalAzn(0.0)
    var onItemClick:((String)->Unit)?=null
//    var onItemClickToPayment:((Float)->Unit)?=null
//    var onItemClickToPaymentAzn:((Float)->Unit)?=null
    var onItemClick1:((String)->Unit)?=null
    var showFirst:Int=1

    private val diffUtil=object :DiffUtil.ItemCallback<BalanceTry>(){
        override fun areItemsTheSame(oldItem: BalanceTry, newItem: BalanceTry): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: BalanceTry, newItem: BalanceTry): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer=AsyncListDiffer(this,diffUtil)
    var balanceList:List<BalanceTry>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)


    private val diffUtil1=object :DiffUtil.ItemCallback<BalanceAzn>(){
        override fun areItemsTheSame(oldItem: BalanceAzn, newItem: BalanceAzn): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: BalanceAzn, newItem: BalanceAzn): Boolean {
            return oldItem==newItem
        }
    }
    private val recyclerDiffer1=AsyncListDiffer(this,diffUtil1)
    var balanceList1:List<BalanceAzn>
        get() = recyclerDiffer1.currentList
        set(value) = recyclerDiffer1.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
        val binding=BalanceRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BalanceViewHolder(binding)
    }

    override fun getItemCount(): Int {
return if (showFirst==1){
    balanceList.size
}else if (showFirst==2){
    balanceList1.size
}else{
    balanceList.size
}
    }

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
        if (showFirst==1){
            val balanceArrayList=balanceList.get(position)

//            if (position==balanceList.size-1){
//                val params=holder.binding.linearlayout2.layoutParams as LinearLayout.LayoutParams
//                params.bottomMargin=45
//                holder.binding.linearlayout2.layoutParams=params
//            }
            holder.binding.balanceItemHistory.text=balanceArrayList.history
            holder.binding.balanceItemPrice.text=balanceArrayList.amount.toString()
            holder.binding.balanceItemBalance.text=balanceArrayList.balance.toString()


            holder.binding.balanceItemView.setOnClickListener {
                    val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_view,null)
                    val close=dialogView.findViewById<Button>(R.id.close)
                    val url=dialogView.findViewById<TextView>(R.id.url)
                    val price=dialogView.findViewById<TextView>(R.id.price)
                    val color=dialogView.findViewById<TextView>(R.id.count)

                    url.text=balanceArrayList.amount.toString()
                    price.text=balanceArrayList.balance.toString()
                    color.text=balanceArrayList.history

                    val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                    alertDialogBuilder.setView(dialogView)

                    val alertDialog=alertDialogBuilder.create()
                    close.setOnClickListener {
                        alertDialog.dismiss()
                    }

                    alertDialog.show()
            }
        }else if (showFirst==2){

            val balanceList1Array = balanceList1.get(position)

//            if (position==balanceList1.size-1){
//                val params=holder.binding.linearlayout2.layoutParams as LinearLayout.LayoutParams
//                params.bottomMargin=45
//                holder.binding.linearlayout2.layoutParams=params
//            }

            holder.binding.balanceItemHistory.text = balanceList1Array.history
            holder.binding.balanceItemPrice.text = balanceList1Array.amount.toString()
            holder.binding.balanceItemBalance.text = balanceList1Array.balance.toString()

            holder.binding.balanceItemView.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_view,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.count)

                url.text=balanceList1Array.amount.toString()
                price.text=balanceList1Array.balance.toString()
                color.text=balanceList1Array.history

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            }else{

        }

    }
}