package com.imranmelikov.zipex.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

   var balanceList2=BalanceTotalTry(0.0)
    var balanceList3= BalanceTotalAzn(0.0)
    var onItemClick:((String)->Unit)?=null
    var onItemClick1:((String)->Unit)?=null
   private var showFirst:Boolean=true

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
return if (showFirst){
    balanceList.size
}else{
    balanceList1.size
}
    }

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
        if (showFirst){
            val myColor= ContextCompat.getColor(holder.itemView.context, R.color.primary)
            val balanceArrayList=balanceList.get(position)
            if (position==0){
                holder.binding.linearbuttons.visibility=View.VISIBLE
                holder.binding.linearcommon.visibility=View.VISIBLE
            }else{
                holder.binding.linearbuttons.visibility=View.GONE
                holder.binding.linearcommon.visibility=View.GONE
            }
//            if (position==balanceList.size-1){
//                val params=holder.binding.linearlayout2.layoutParams as LinearLayout.LayoutParams
//                params.bottomMargin=45
//                holder.binding.linearlayout2.layoutParams=params
//            }

            holder.binding.balanceItemService.setBackgroundColor(Color.WHITE)
            holder.binding.balanceItemService.setTextColor(myColor)
            holder.binding.balanceItemOrder.setTextColor(Color.WHITE)
            holder.binding.balanceItemOrder.setBackgroundColor(myColor)

            holder.binding.balanceItemAddbalancebutton.setOnClickListener {
                if (holder.binding.balanceItemAddbalance.text.toString().isEmpty()){
                    Toast.makeText(holder.itemView.context,"Balansı qeyd edin",Toast.LENGTH_SHORT).show()
                }else{
                    val amount=holder.binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(BalanceFragmentDirections.actionBalanceFragmentToPaymentFragment(amount))
                }
            }
            holder.binding.balanceItemHistory.text=balanceArrayList.history
            holder.binding.balanceItemPrice.text=balanceArrayList.amount.toString()
            holder.binding.balanceItemBalance.text=balanceArrayList.balance.toString()
        holder.binding.balanceTitleBalance.text="Balans : ${balanceList2.balanceTotal.toString()} TL"

            holder.binding.balanceItemService.setOnClickListener {view->
                showFirst=false
                Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
                onItemClick?.let {
                    it("a")
                }
            }
        }else {

            val balanceList1Array = balanceList1.get(position)
            val myColor = ContextCompat.getColor(holder.itemView.context, R.color.primary)

            if (position==0){
                holder.binding.linearbuttons.visibility=View.VISIBLE
                holder.binding.linearcommon.visibility=View.VISIBLE
            }else{
                holder.binding.linearbuttons.visibility=View.GONE
                holder.binding.linearcommon.visibility=View.GONE
            }

//            if (position==balanceList1.size-1){
//                val params=holder.binding.linearlayout2.layoutParams as LinearLayout.LayoutParams
//                params.bottomMargin=45
//                holder.binding.linearlayout2.layoutParams=params
//            }


            holder.binding.balanceItemService.setBackgroundColor(myColor)
            holder.binding.balanceItemService.setTextColor(Color.WHITE)
            holder.binding.balanceItemOrder.setTextColor(myColor)
            holder.binding.balanceItemOrder.setBackgroundColor(Color.WHITE)

            holder.binding.balanceItemAddbalancebutton.setOnClickListener {
                if (holder.binding.balanceItemAddbalance.text.toString().isEmpty()) {
                    Toast.makeText(holder.itemView.context, "Balansı qeyd edin", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val amount = holder.binding.balanceItemAddbalance.text.toString().toFloat()
                    Navigation.findNavController(it).navigate(
                        BalanceFragmentDirections.actionBalanceFragmentToPaymentAznFragment(amount)
                    )
                }
            }

            holder.binding.balanceItemHistory.text = balanceList1Array.history
            holder.binding.balanceItemPrice.text = balanceList1Array.amount.toString()
            holder.binding.balanceItemBalance.text = balanceList1Array.balance.toString()
            holder.binding.balanceTitleBalance.text="Balans : ${balanceList3.balanceTotal.toString()} AZN"
            holder.binding.balanceItemOrder.setOnClickListener {view->
                showFirst = true
                Navigation.findNavController(view).navigate(BalanceFragmentDirections.actionBalanceFragmentToHomeFragment())
                onItemClick1?.let {
                    it("b")
                }
        }
            }


        holder.binding.balanceItemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Məlumatlara ətraflı baxmaq mümkün olmadı",Toast.LENGTH_SHORT).show()
        }
    }
}