package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.DebtRowBinding
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.Debt
import com.imranmelikov.zipex.model.DebtTotal
import com.imranmelikov.zipex.view.DebtFragmentDirections
import javax.inject.Inject

class DebtAdapter @Inject constructor():RecyclerView.Adapter<DebtAdapter.DebtViewHolder>() {
    class DebtViewHolder(var binding:DebtRowBinding):RecyclerView.ViewHolder(binding.root)

//    var debtAznTotal=DebtTotal(0.0)
    var onItemClickPay:((Debt)->Unit)?=null
//    var onItemClickPayAll:((DebtTotal)->Unit)?=null
    var onItemClickOnlinePay:((Debt)->Unit)?=null
//    var onItemClickOnlinePayAll:((DebtTotal)->Unit)?=null
    private val diffUtil=object : DiffUtil.ItemCallback<Debt>(){
        override fun areItemsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Debt, newItem: Debt): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer= AsyncListDiffer(this,diffUtil)
    var debtList:List<Debt>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val binding=DebtRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DebtViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return debtList.size
    }

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {

        val debtArrayList=debtList.get(position)

        holder.binding.debtHistory.text=debtArrayList.history
        holder.binding.debtPrice.text=debtArrayList.amount.toString()



        holder.binding.debtPay.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_payment,null)
            val no=dialogView.findViewById<Button>(R.id.no)
            val yes=dialogView.findViewById<Button>(R.id.yes)

            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            no.setOnClickListener {
                onItemClickPay?.let {
                    it(debtArrayList)
                }
                alertDialog.dismiss()
            }
            yes.setOnClickListener {
                onItemClickOnlinePay?.let {
                    it(debtArrayList)
                }
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
        holder.binding.debtView.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_debt_view,null)
            val close=dialogView.findViewById<Button>(R.id.close)
            val history=dialogView.findViewById<TextView>(R.id.price)
            val amount=dialogView.findViewById<TextView>(R.id.url)

            history.text=debtArrayList.history
            amount.text=debtArrayList.amount.toString()

            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            close.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}