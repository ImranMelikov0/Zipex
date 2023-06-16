package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.CartRowBinding
import com.imranmelikov.zipex.model.Link
import javax.inject.Inject

class CartAdapter @Inject constructor():RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(var binding:CartRowBinding):RecyclerView.ViewHolder(binding.root)

    var onItemClickCartUpdate:((Link)->Unit)?=null
    var onItemClickDelete:((Link)->Unit)?=null

    private val diffUtil=object :DiffUtil.ItemCallback<Link>(){
        override fun areItemsTheSame(oldItem: Link, newItem: Link): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Link, newItem: Link): Boolean {
            return oldItem==newItem
        }
    }
    private val recyclerDiffer=AsyncListDiffer(this,diffUtil)

    var cartList:List<Link>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding=CartRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartArraylist=cartList.get(position)
        holder.binding.cartIdtitle.text=cartArraylist.uuid.toString()
        holder.binding.cartColor.text=cartArraylist.color
        holder.binding.cartComment.text=cartArraylist.comment
        holder.binding.cartHistory.text=cartArraylist.history
        holder.binding.cartQuantity.text=cartArraylist.count.toString()
        holder.binding.cartSize.text=cartArraylist.size
        holder.binding.cartPrice.text=cartArraylist.price.toDouble().toString()

        holder.binding.cartUpdate.setOnClickListener {
            val dialogview=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_cart_update,null)
            val editLink=dialogview.findViewById<EditText>(R.id.editLink)
            val editPrice=dialogview.findViewById<EditText>(R.id.editCategory)
            val editQuantity=dialogview.findViewById<EditText>(R.id.editquantity)
            val button=dialogview.findViewById<Button>(R.id.button)

            editLink.setText(cartArraylist.url)
            editPrice.setText(cartArraylist.price.toString())
            editQuantity.setText(cartArraylist.count.toString())


            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogview)

            val alertDialog=alertDialogBuilder.create()

            button.setOnClickListener {
                if (editLink.text.toString().isEmpty()||editPrice.text.toString().isEmpty()||editQuantity.text.toString().isEmpty()){
                    Toast.makeText(holder.itemView.context,"Məlumatları daxil edin",Toast.LENGTH_SHORT).show()
                }else{
                    val cart=Link(editLink.text.toString(),cartArraylist.category
                        ,editQuantity.text.toString().toInt(),cartArraylist.color,cartArraylist.size,
                        editPrice.text.toString().toInt(),cartArraylist.comment,cartArraylist.history)
                    cart.uuid=cartArraylist.uuid
                    onItemClickCartUpdate?.let {
                        it(cart)
                    }
                    alertDialog.dismiss()
                }
            }
            alertDialog.show()
        }
        holder.binding.cartDelete.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_delete,null)
            val no=dialogView.findViewById<Button>(R.id.no)
            val yes=dialogView.findViewById<Button>(R.id.yes)

            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            no.setOnClickListener {
                alertDialog.dismiss()
            }
            yes.setOnClickListener {
                onItemClickDelete?.let {
                    it(cartArraylist)
                }
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
        holder.binding.cartLink.setOnClickListener {
            val clipboard=holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip=ClipData.newPlainText("label",cartArraylist.url)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(holder.itemView.context,"Link kopyalandı",Toast.LENGTH_SHORT).show()
        }
    }
}