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
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.DebtaznRowBinding
import com.imranmelikov.zipex.model.Debt
import com.imranmelikov.zipex.model.DebtHistory
import javax.inject.Inject

class DebtHistoryAdapter @Inject constructor():RecyclerView.Adapter<DebtHistoryAdapter.DebtHistoryViewHolder>() {
    class DebtHistoryViewHolder(var binding:DebtaznRowBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object : DiffUtil.ItemCallback<DebtHistory>(){
        override fun areItemsTheSame(oldItem: DebtHistory, newItem: DebtHistory): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: DebtHistory, newItem: DebtHistory): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer= AsyncListDiffer(this,diffUtil)
    var debtHistoryList:List<DebtHistory>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtHistoryViewHolder {
        val binding=DebtaznRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DebtHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return debtHistoryList.size
    }

    override fun onBindViewHolder(holder: DebtHistoryViewHolder, position: Int) {
        val debtHistoryArrayList=debtHistoryList.get(position)

        holder.binding.debthistoryHistory.text=debtHistoryArrayList.history
        holder.binding.debthistoryPrice.text="${debtHistoryArrayList.amount} AZN"


        holder.binding.debthistoryView.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_debt_view,null)
            val close=dialogView.findViewById<Button>(R.id.close)
            val history=dialogView.findViewById<TextView>(R.id.price)
            val amount=dialogView.findViewById<TextView>(R.id.url)

            history.text=debtHistoryArrayList.history
            amount.text=debtHistoryArrayList.amount.toString()

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