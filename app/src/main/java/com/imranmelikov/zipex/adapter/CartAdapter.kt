package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.CartRowBinding
import com.imranmelikov.zipex.model.CustomToast
import com.imranmelikov.zipex.model.Link
import com.imranmelikov.zipex.view.CartFragmentDirections
import com.imranmelikov.zipex.view.WebViewActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class CartAdapter @Inject constructor():RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(var binding:CartRowBinding):RecyclerView.ViewHolder(binding.root)

    var onItemClickCartUpdate:((Link)->Unit)?=null
    var onItemClickDelete:((Link)->Unit)?=null
    var onItemClickBalancePay:((Link)->Unit)?=null
    var onItemClickBalancePayUsd:((Link)->Unit)?=null
    var onItemClickOnlinePay:((Link)->Unit)?=null
    var onItemClickOnlinePayUsd:((Link)->Unit)?=null
    var onItemClickUpdateSigorta:((Link)->Unit)?=null

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
        holder.binding.cartIdtitle.text=cartArraylist.url
        holder.binding.cartColor.text="Rəng: ${cartArraylist.color}"
        holder.binding.cartComment.text="Qeyd: ${cartArraylist.comment}"
        holder.binding.cartHistory.text="Tarix: ${cartArraylist.history}"
        holder.binding.cartQuantity.text="Say: ${cartArraylist.count.toString()}"
        holder.binding.cartSize.text="Ölçü: ${cartArraylist.size}"
        holder.binding.cartCountry.text="Ölkə: ${cartArraylist.country}"
        if (cartArraylist.country.equals("Türkiye")){
            holder.binding.cartPrice.text="Qiymət: ${cartArraylist.price} TRY"
        }else{
            holder.binding.cartPrice.text="Qiymət: ${cartArraylist.price} USD"
        }

        if (cartArraylist.payment.equals("Ödənilib")){
            holder.binding.cartUpdate.visibility=View.GONE
            holder.binding.cartDelete.visibility=View.GONE
            holder.binding.cartBalancePay.visibility=View.GONE
            holder.binding.cartOnlinePay.visibility=View.GONE
        }else{

        }
        if (cartArraylist.sigorta.equals("Sığortalanıb")){
            holder.binding.SigortaText.text=cartArraylist.sigorta
            holder.binding.cartSigorta.visibility=View.GONE
            val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
            holder.binding.SigortaText.setTextColor(color)
        }else{

        }

        val spannableStringColor=SpannableString(holder.binding.cartColor.text)
        val endlengthColor=5
        spannableStringColor.setSpan(StyleSpan(Typeface.BOLD),endlengthColor,holder.binding.cartColor.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartColor.text=spannableStringColor

        val spannableStringComment=SpannableString(holder.binding.cartComment.text)
        val endlengthComment=5
        spannableStringComment.setSpan(StyleSpan(Typeface.BOLD),endlengthComment,holder.binding.cartComment.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartComment.text=spannableStringComment

        val spannableStringHistory=SpannableString(holder.binding.cartHistory.text)
        val endlengthHistory=6
        spannableStringHistory.setSpan(StyleSpan(Typeface.BOLD),endlengthHistory,holder.binding.cartHistory.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartHistory.text=spannableStringHistory

        val spannableStringQuantity=SpannableString(holder.binding.cartQuantity.text)
        val endlengthQuantity=4
        spannableStringQuantity.setSpan(StyleSpan(Typeface.BOLD),endlengthQuantity,holder.binding.cartQuantity.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartQuantity.text=spannableStringQuantity

        val spannableStringSize=SpannableString(holder.binding.cartSize.text)
        val endlengthSize=5
        spannableStringSize.setSpan(StyleSpan(Typeface.BOLD),endlengthSize,holder.binding.cartSize.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartSize.text=spannableStringSize

        val spannableStringPrice=SpannableString(holder.binding.cartPrice.text)
        val endlengthPrice=7
        spannableStringPrice.setSpan(StyleSpan(Typeface.BOLD),endlengthPrice,holder.binding.cartPrice.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartPrice.text=spannableStringPrice

        val spannableStringCountry=SpannableString(holder.binding.cartCountry.text)
        val endlengthCountry=4
        spannableStringCountry.setSpan(StyleSpan(Typeface.BOLD),endlengthCountry,holder.binding.cartCountry.text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.binding.cartCountry.text=spannableStringCountry

        if (cartArraylist.payment.equals("Ödənilməyib")){
            val text="Ödəniş: Ödənilməyib"
            val spannableString= SpannableString(text)
            val endLength=7
            val color=R.color.red
            val colorPrimary=R.color.primary
            spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(holder.itemView.context,colorPrimary)),0,endLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(holder.itemView.context,color)),endLength,text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),endLength,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.binding.cartPayment.text=spannableString
        }else{
            val text="Ödənilib"
            val spannableString= SpannableString(text)
            val endLength=0
            val color=R.color.green
            val colorPrimary=R.color.primary
            spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(holder.itemView.context,colorPrimary)),0,endLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(holder.itemView.context,color)),endLength,text.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(StyleSpan(Typeface.BOLD),endLength,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.binding.cartPayment.text=spannableString
        }


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
                if (editLink.text.toString().isEmpty()||editPrice.text.toString().toDoubleOrNull()==null||editQuantity.text.toString().isEmpty()||editPrice.text.toString().toDouble()==0.0){
                    val customToast = CustomToast(holder.itemView.context)
                    customToast.showToast("Məlumatları daxil edin")
                }else{
                    val price= editPrice.text.toString().toDouble()
                    val decimalFormat = DecimalFormat("#.##")
                    decimalFormat.roundingMode = RoundingMode.HALF_UP
                    val roundedAmount = decimalFormat.format(price).toDouble()
                    val cart=Link(editLink.text.toString(),cartArraylist.category
                        ,editQuantity.text.toString().toInt(),cartArraylist.color,cartArraylist.size,
                        roundedAmount,cartArraylist.comment,cartArraylist.history,cartArraylist.country,cartArraylist.sigorta,cartArraylist.payment)
                    cart.uuid=cartArraylist.uuid
                    onItemClickCartUpdate?.let {
                        it(cart)
                    }
                    val customToast = CustomToast(holder.itemView.context)
                    customToast.showToast("Məlumat əlavə olundu")
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
                val customToast = CustomToast(holder.itemView.context)
                customToast.showToast("Məlumat silindi")

                alertDialog.dismiss()
            }
            alertDialog.show()
        }
        holder.binding.cartLink.setOnClickListener {
            val clipboard=holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip=ClipData.newPlainText("label",cartArraylist.url)
            clipboard.setPrimaryClip(clip)
            val customToast = CustomToast(holder.itemView.context)
            customToast.showToast("Link kopyalandı")
        }

        holder.binding.cartSigorta.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_sigorta,null)
            val no=dialogView.findViewById<Button>(R.id.no)
            val yes=dialogView.findViewById<Button>(R.id.yes)

            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            no.setOnClickListener {
                alertDialog.dismiss()
            }
            yes.setOnClickListener {
                val cartUpdateSigorta=Link(cartArraylist.url,cartArraylist.category,cartArraylist.count,cartArraylist.color,cartArraylist.size,cartArraylist.price,cartArraylist.comment,cartArraylist.history,cartArraylist.country,"Sığortalanıb",cartArraylist.payment)
                cartUpdateSigorta.uuid=cartArraylist.uuid
                onItemClickUpdateSigorta?.let {
                    it(cartUpdateSigorta)
                }
                val customToast = CustomToast(holder.itemView.context)
                customToast.showToast("Sifariş uğurla sığortalandı!")
                holder.binding.SigortaText.text=cartArraylist.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.SigortaText.setTextColor(color)
                holder.binding.cartSigorta.visibility=View.GONE
                alertDialog.dismiss()
            }
            alertDialog.show()
        }

        holder.binding.cartView.setOnClickListener {
            if (Patterns.WEB_URL.matcher(cartArraylist.url).matches()) {
                val intent=Intent(holder.itemView.context,WebViewActivity::class.java)
                intent.putExtra("keyCart",cartArraylist.url)
                holder.itemView.context.startActivity(intent)
            } else {
                val customToast = CustomToast(holder.itemView.context)
                customToast.showToast("Sayta keçmək mümkün olmadı")
            }
        }
        holder.binding.balancebuttonpay.setOnClickListener {
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_pay_balance,null)
            val no=dialogView.findViewById<Button>(R.id.no)
            val yes=dialogView.findViewById<Button>(R.id.yes)

            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogView)

            val alertDialog=alertDialogBuilder.create()
            no.setOnClickListener {
                alertDialog.dismiss()
            }
            yes.setOnClickListener {
                if (cartArraylist.country=="Amerika"){
                    onItemClickBalancePayUsd?.let {
                        it(cartArraylist)
                    }
                }else{
                    onItemClickBalancePay?.let {
                        it(cartArraylist)
                    }
                }
                alertDialog.dismiss()
            }
            alertDialog.show()

        }
        holder.binding.onlinePay.setOnClickListener {view->
            if (cartArraylist.country=="Amerika"){
                Navigation.findNavController(view).navigate(CartFragmentDirections.actionCartFragmentToPaymentUsdFragment())
                onItemClickOnlinePayUsd?.let {
                    it(cartArraylist)
                }
            }else {
                Navigation.findNavController(view)
                    .navigate(CartFragmentDirections.actionCartFragmentToPaymentFragment(2F, 2))
                onItemClickOnlinePay?.let {
                    it(cartArraylist)
                }
            }
        }
    }
}