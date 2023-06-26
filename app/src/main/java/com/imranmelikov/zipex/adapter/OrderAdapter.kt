package com.imranmelikov.zipex.adapter

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.AdmindetailRowBinding
import com.imranmelikov.zipex.databinding.OrderItemRowBinding
import com.imranmelikov.zipex.model.BalanceTry
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order5
import com.imranmelikov.zipex.model.Order6
import com.imranmelikov.zipex.model.Order7
import com.imranmelikov.zipex.model.Order8
import com.imranmelikov.zipex.model.Order9
import com.imranmelikov.zipex.view.OrderFragmentDirections
import com.imranmelikov.zipex.view.RegularOrderFragmentDirections
import javax.inject.Inject

class OrderAdapter @Inject constructor():RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(var binding:OrderItemRowBinding):RecyclerView.ViewHolder(binding.root)

    var showFirst:Int=1
    var onItemClickOrder1:((Order1)->Unit)?=null
    var onItemClickOrder2:((Order2)->Unit)?=null
    var onItemClickOrder3:((Order3)->Unit)?=null

    private val diffUtil=object : DiffUtil.ItemCallback<Order1>(){
        override fun areItemsTheSame(oldItem: Order1, newItem: Order1): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order1, newItem: Order1): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer= AsyncListDiffer(this,diffUtil)
    var order1List:List<Order1>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    private val diffUtil2=object : DiffUtil.ItemCallback<Order2>(){
        override fun areItemsTheSame(oldItem: Order2, newItem: Order2): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order2, newItem: Order2): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer2= AsyncListDiffer(this,diffUtil2)
    var order2List:List<Order2>
        get() = recyclerDiffer2.currentList
        set(value) = recyclerDiffer2.submitList(value)

    private val diffUtil3=object : DiffUtil.ItemCallback<Order3>(){
        override fun areItemsTheSame(oldItem: Order3, newItem: Order3): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order3, newItem: Order3): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer3= AsyncListDiffer(this,diffUtil3)
    var order3List:List<Order3>
        get() = recyclerDiffer3.currentList
        set(value) = recyclerDiffer3.submitList(value)

    private val diffUtil4=object : DiffUtil.ItemCallback<Order4>(){
        override fun areItemsTheSame(oldItem: Order4, newItem: Order4): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order4, newItem: Order4): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer4= AsyncListDiffer(this,diffUtil4)
    var order4List:List<Order4>
        get() = recyclerDiffer4.currentList
        set(value) = recyclerDiffer4.submitList(value)

    private val diffUtil5=object : DiffUtil.ItemCallback<Order5>(){
        override fun areItemsTheSame(oldItem: Order5, newItem: Order5): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order5, newItem: Order5): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer5= AsyncListDiffer(this,diffUtil5)
    var order5List:List<Order5>
        get() = recyclerDiffer5.currentList
        set(value) = recyclerDiffer5.submitList(value)

    private val diffUtil6=object : DiffUtil.ItemCallback<Order6>(){
        override fun areItemsTheSame(oldItem: Order6, newItem: Order6): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order6, newItem: Order6): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer6= AsyncListDiffer(this,diffUtil6)
    var order6List:List<Order6>
        get() = recyclerDiffer6.currentList
        set(value) = recyclerDiffer6.submitList(value)

    private val diffUtil7=object : DiffUtil.ItemCallback<Order7>(){
        override fun areItemsTheSame(oldItem: Order7, newItem: Order7): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order7, newItem: Order7): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer7= AsyncListDiffer(this,diffUtil7)
    var order7List:List<Order7>
        get() = recyclerDiffer7.currentList
        set(value) = recyclerDiffer7.submitList(value)

    private val diffUtil8=object : DiffUtil.ItemCallback<Order8>(){
        override fun areItemsTheSame(oldItem: Order8, newItem: Order8): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order8, newItem: Order8): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer8= AsyncListDiffer(this,diffUtil8)
    var order8List:List<Order8>
        get() = recyclerDiffer8.currentList
        set(value) = recyclerDiffer8.submitList(value)

    private val diffUtil9=object : DiffUtil.ItemCallback<Order9>(){
        override fun areItemsTheSame(oldItem: Order9, newItem: Order9): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Order9, newItem: Order9): Boolean {
            return oldItem==newItem
        }

    }
    private val recyclerDiffer9= AsyncListDiffer(this,diffUtil9)
    var order9List:List<Order9>
        get() = recyclerDiffer9.currentList
        set(value) = recyclerDiffer9.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding=OrderItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (showFirst==1){
            order1List.size
        }else if (showFirst==2){
            order2List.size
        }else if (showFirst==3){
            order3List.size
        }else if (showFirst==4){
            order4List.size
        }else if (showFirst==5){
            order5List.size
        }else if(showFirst==6){
            order6List.size
        }else if (showFirst==7){
            order7List.size
        }else if (showFirst==8){
            order8List.size
        }else{
            order9List.size
        }
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.binding.orderItemImage.setOnClickListener {
            val dialogview=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_imagefile,null)
            val button=dialogview.findViewById<Button>(R.id.dialog_button)
            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogview)

            val alertDialog=alertDialogBuilder.create()

            button.setOnClickListener {

                alertDialog.dismiss()
            }
            alertDialog.show()
        }
        holder.binding.imagePen.setOnClickListener {
            val dialogview=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_imagepen,null)
            val button=dialogview.findViewById<Button>(R.id.dialog_button)
            val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setView(dialogview)

            val alertDialog=alertDialogBuilder.create()

            button.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }

        if (showFirst==1){
            val order1ArrayList=order1List.get(position)
            holder.binding.idTitle.text="NW${order1ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order1ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order1ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order1ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order1ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order1ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: 0.00"
            holder.binding.orderItemWeight.text="Çəki: 0.00"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order1ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order1ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
                holder.binding.cartSigorta.visibility=View.GONE
            }else{

            }


            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

               history.text=order1ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order1ArrayList.url
                if (order1ArrayList.country=="Amerika"){
                    price.text="${order1ArrayList.price} USD"
                }else{
                    price.text="${order1ArrayList.price} TRY"
                }
                color.text=order1ArrayList.color
                count.text=order1ArrayList.count.toString()
                size.text=order1ArrayList.size
                comment.text=order1ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
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
                   onItemClickOrder1?.let {
                       it(order1ArrayList)
                   }
                    Toast.makeText(holder.itemView.context,"Sifariş uğurla sığortalandı!",Toast.LENGTH_SHORT).show()
                    holder.binding.sigorta.text=order1ArrayList.sigorta
                    val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                    holder.binding.sigorta.setTextColor(color)
                    holder.binding.cartSigorta.visibility=View.GONE
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }


            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

         val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

              val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

             val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if (showFirst==2){
                val order2ArrayList=order2List.get(position)
                holder.binding.idTitle.text="NW${order2ArrayList.uuid.toString()}"
                holder.binding.orderItemCategory.text="Kateqoriya: ${order2ArrayList.category}"
                holder.binding.orderItemHistory.text="Tarix: ${order2ArrayList.history}"
                holder.binding.orderItemPrice.text="Qiymət: ${order2ArrayList.price}"
                holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order2ArrayList.marketCode}"
                holder.binding.orderItemMarketname.text="Mağaza adı: ${order2ArrayList.marketName}"
                holder.binding.orderItemCatdirilma.text="Çatdırılma: 0.00"
                holder.binding.orderItemWeight.text="Çəki: 0.00"
                holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order2ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order2ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
                holder.binding.cartSigorta.visibility=View.GONE
            }else{

            }



            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order2ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order2ArrayList.url
                if (order2ArrayList.country=="Amerika"){
                    price.text="${order2ArrayList.price} USD"
                }else{
                    price.text="${order2ArrayList.price} TRY"
                }
                color.text=order2ArrayList.color
                count.text=order2ArrayList.count.toString()
                size.text=order2ArrayList.size
                comment.text=order2ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
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
                    onItemClickOrder2?.let {
                        it(order2ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Sifariş uğurla sığortalandı!",Toast.LENGTH_SHORT).show()
                    holder.binding.sigorta.text=order2ArrayList.sigorta
                    val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                    holder.binding.sigorta.setTextColor(color)
                    holder.binding.cartSigorta.visibility=View.GONE
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }


                val textCategory = holder.binding.orderItemCategory.text.toString()
                if (textCategory.length > 11) {
                    val spannableStringOffice = SpannableString(textCategory)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textCategory.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCategory.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCategory.text = textCategory
                }

                val textHistory = holder.binding.orderItemHistory.text.toString()
                if (textHistory.length > 6) {
                    val spannableStringOffice = SpannableString(textHistory)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        6,
                        textHistory.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemHistory.text = spannableStringOffice
                } else {
                    holder.binding.orderItemHistory.text = textHistory
                }


                val textPrice = holder.binding.orderItemPrice.text.toString()
                if (textPrice.length > 7) {
                    val spannableStringOffice = SpannableString(textPrice)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        7,
                        textPrice.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemPrice.text = spannableStringOffice
                } else {
                    holder.binding.orderItemPrice.text = textPrice
                }

                val textMarket = holder.binding.orderItemMarketname.text.toString()
                if (textMarket.length > 11) {
                    val spannableStringOffice = SpannableString(textMarket)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textMarket.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemMarketname.text = spannableStringOffice
                } else {
                    holder.binding.orderItemMarketname.text = textMarket
                }
                val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
                if (textOffice.length > 17) {
                    val spannableStringOffice = SpannableString(textOffice)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        17,
                        textOffice.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCatdirilmaofis.text = textOffice
                }

                val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
                if (textDelivery.length > 11) {
                    val spannableStringOffice = SpannableString(textDelivery)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textDelivery.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCatdirilma.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCatdirilma.text = textDelivery
                }

                val textWeight = holder.binding.orderItemWeight.text.toString()
                if (textWeight.length > 5) {
                    val spannableStringOffice = SpannableString(textWeight)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        5,
                        textWeight.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemWeight.text = spannableStringOffice
                } else {
                    holder.binding.orderItemWeight.text = textWeight
                }

                val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
                if (textMarketCode.length > 12) {
                    val spannableStringOffice = SpannableString(textMarketCode)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        12,
                        textMarketCode.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemMarketcode.text = spannableStringOffice
                } else {
                    holder.binding.orderItemMarketcode.text = textMarketCode
                }
        }else if (showFirst==3){
                val order3ArrayList=order3List.get(position)
                holder.binding.idTitle.text="NW${order3ArrayList.uuid.toString()}"
                holder.binding.orderItemCategory.text="Kateqoriya: ${order3ArrayList.category}"
                holder.binding.orderItemHistory.text="Tarix: ${order3ArrayList.history}"
                holder.binding.orderItemPrice.text="Qiymət: ${order3ArrayList.price}"
                holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order3ArrayList.marketCode}"
                holder.binding.orderItemMarketname.text="Mağaza adı: ${order3ArrayList.marketName}"
                holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order3ArrayList.delivery.toDouble()}"
                holder.binding.orderItemWeight.text="Çəki: ${order3ArrayList.weight.toDouble()}"
                holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order3ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order3ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
                holder.binding.cartSigorta.visibility=View.GONE
            }else{

            }

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order3ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order3ArrayList.url
                if (order3ArrayList.country=="Amerika"){
                    price.text="${order3ArrayList.price} USD"
                }else{
                    price.text="${order3ArrayList.price} TRY"
                }
                color.text=order3ArrayList.color
                count.text=order3ArrayList.count.toString()
                size.text=order3ArrayList.size
                comment.text=order3ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
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
                    onItemClickOrder3?.let {
                        it(order3ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Sifariş uğurla sığortalandı!",Toast.LENGTH_SHORT).show()
                    holder.binding.sigorta.text=order3ArrayList.sigorta
                    val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                    holder.binding.sigorta.setTextColor(color)
                    holder.binding.cartSigorta.visibility=View.GONE
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }
                val textCategory = holder.binding.orderItemCategory.text.toString()
                if (textCategory.length > 11) {
                    val spannableStringOffice = SpannableString(textCategory)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textCategory.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCategory.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCategory.text = textCategory
                }

                val textHistory = holder.binding.orderItemHistory.text.toString()
                if (textHistory.length > 6) {
                    val spannableStringOffice = SpannableString(textHistory)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        6,
                        textHistory.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemHistory.text = spannableStringOffice
                } else {
                    holder.binding.orderItemHistory.text = textHistory
                }


                val textPrice = holder.binding.orderItemPrice.text.toString()
                if (textPrice.length > 7) {
                    val spannableStringOffice = SpannableString(textPrice)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        7,
                        textPrice.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemPrice.text = spannableStringOffice
                } else {
                    holder.binding.orderItemPrice.text = textPrice
                }

                val textMarket = holder.binding.orderItemMarketname.text.toString()
                if (textMarket.length > 11) {
                    val spannableStringOffice = SpannableString(textMarket)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textMarket.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemMarketname.text = spannableStringOffice
                } else {
                    holder.binding.orderItemMarketname.text = textMarket
                }
                val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
                if (textOffice.length > 17) {
                    val spannableStringOffice = SpannableString(textOffice)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        17,
                        textOffice.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCatdirilmaofis.text = textOffice
                }

                val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
                if (textDelivery.length > 11) {
                    val spannableStringOffice = SpannableString(textDelivery)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        11,
                        textDelivery.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemCatdirilma.text = spannableStringOffice
                } else {
                    holder.binding.orderItemCatdirilma.text = textDelivery
                }

                val textWeight = holder.binding.orderItemWeight.text.toString()
                if (textWeight.length > 5) {
                    val spannableStringOffice = SpannableString(textWeight)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        5,
                        textWeight.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemWeight.text = spannableStringOffice
                } else {
                    holder.binding.orderItemWeight.text = textWeight
                }

                val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
                if (textMarketCode.length > 12) {
                    val spannableStringOffice = SpannableString(textMarketCode)
                    spannableStringOffice.setSpan(
                        StyleSpan(Typeface.BOLD),
                        12,
                        textMarketCode.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    holder.binding.orderItemMarketcode.text = spannableStringOffice
                } else {
                    holder.binding.orderItemMarketcode.text = textMarketCode
                }
        }else if (showFirst==4){
            val order4ArrayList=order4List.get(position)
            holder.binding.idTitle.text="NW${order4ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order4ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order4ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order4ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order4ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order4ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order4ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order4ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order4ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order4ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }

                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE


            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order4ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order4ArrayList.url
                if (order4ArrayList.country=="Amerika"){
                    price.text="${order4ArrayList.price} USD"
                }else{
                    price.text="${order4ArrayList.price} TRY"
                }
                color.text=order4ArrayList.color
                count.text=order4ArrayList.count.toString()
                size.text=order4ArrayList.size
                comment.text=order4ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }

            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if (showFirst==5){
            val order5ArrayList=order5List.get(position)
            holder.binding.idTitle.text="NW${order5ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order5ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order5ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order5ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order5ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order5ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order5ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order5ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"


            if (order5ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order5ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }
                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order5ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order5ArrayList.url
                if (order5ArrayList.country=="Amerika"){
                    price.text="${order5ArrayList.price} USD"
                }else{
                    price.text="${order5ArrayList.price} TRY"
                }
                color.text=order5ArrayList.color
                count.text=order5ArrayList.count.toString()
                size.text=order5ArrayList.size
                comment.text=order5ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if (showFirst==6){
            val order6ArrayList=order6List.get(position)
            holder.binding.idTitle.text="NW${order6ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order6ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order6ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order6ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order6ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order6ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order6ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order6ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order6ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order6ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }

                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order6ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order6ArrayList.url
                if (order6ArrayList.country=="Amerika"){
                    price.text="${order6ArrayList.price} USD"
                }else{
                    price.text="${order6ArrayList.price} TRY"
                }
                color.text=order6ArrayList.color
                count.text=order6ArrayList.count.toString()
                size.text=order6ArrayList.size
                comment.text=order6ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }

            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if(showFirst==7){
            val order7ArrayList=order7List.get(position)
            holder.binding.idTitle.text="NW${order7ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order7ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order7ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order7ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order7ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order7ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order7ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order7ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order7ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order7ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }

                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order7ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order7ArrayList.url
                if (order7ArrayList.country=="Amerika"){
                    price.text="${order7ArrayList.price} USD"
                }else{
                    price.text="${order7ArrayList.price} TRY"
                }
                color.text=order7ArrayList.color
                count.text=order7ArrayList.count.toString()
                size.text=order7ArrayList.size
                comment.text=order7ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }


            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if(showFirst==8){
            val order8ArrayList=order8List.get(position)
            holder.binding.idTitle.text="NW${order8ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order8ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order8ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order8ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order8ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order8ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order8ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order8ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order8ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order8ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }

                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order8ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order8ArrayList.url
                if (order8ArrayList.country=="Amerika"){
                    price.text="${order8ArrayList.price} USD"
                }else{
                    price.text="${order8ArrayList.price} TRY"
                }
                color.text=order8ArrayList.color
                count.text=order8ArrayList.count.toString()
                size.text=order8ArrayList.size
                comment.text=order8ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }else if (showFirst==9){
            val order9ArrayList=order9List.get(position)
            holder.binding.idTitle.text="NW${order9ArrayList.uuid.toString()}"
            holder.binding.orderItemCategory.text="Kateqoriya: ${order9ArrayList.category}"
            holder.binding.orderItemHistory.text="Tarix: ${order9ArrayList.history}"
            holder.binding.orderItemPrice.text="Qiymət: ${order9ArrayList.price}"
            holder.binding.orderItemMarketcode.text="Mağaza kodu: ${order9ArrayList.marketCode}"
            holder.binding.orderItemMarketname.text="Mağaza adı: ${order9ArrayList.marketName}"
            holder.binding.orderItemCatdirilma.text="Çatdırılma: ${order9ArrayList.delivery.toDouble()}"
            holder.binding.orderItemWeight.text="Çəki: ${order9ArrayList.weight.toDouble()}"
            holder.binding.orderItemCatdirilmaofis.text="Çatdırılma ofisi: Azərbaycanda hər hansısa bir yer"

            if (order9ArrayList.sigorta.equals("Sığortalanıb")){
                holder.binding.sigorta.text=order9ArrayList.sigorta
                val color=ContextCompat.getColor(holder.itemView.context,R.color.green)
                holder.binding.sigorta.setTextColor(color)
            }else{

            }

                holder.binding.imagePen.visibility=View.GONE
                holder.binding.cartSigorta.visibility=View.GONE

            holder.binding.orderItemTimeHistory.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_balance_history,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val history=dialogView.findViewById<TextView>(R.id.history)

                history.text=order9ArrayList.history
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }
            holder.binding.orderItemLinkDetails.setOnClickListener {

                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_detail_link,null)
                val close=dialogView.findViewById<Button>(R.id.close)
                val url=dialogView.findViewById<TextView>(R.id.url)
                val price=dialogView.findViewById<TextView>(R.id.price)
                val color=dialogView.findViewById<TextView>(R.id.color)
                val count=dialogView.findViewById<TextView>(R.id.count)
                val size=dialogView.findViewById<TextView>(R.id.size)
                val comment=dialogView.findViewById<TextView>(R.id.comment)

                url.text=order9ArrayList.url
                if (order9ArrayList.country=="Amerika"){
                    price.text="${order9ArrayList.price} USD"
                }else{
                    price.text="${order9ArrayList.price} TRY"
                }
                color.text=order9ArrayList.color
                count.text=order9ArrayList.count.toString()
                size.text=order9ArrayList.size
                comment.text=order9ArrayList.comment
                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                close.setOnClickListener {
                    alertDialog.dismiss()
                }

                alertDialog.show()
            }

            val textCategory = holder.binding.orderItemCategory.text.toString()
            if (textCategory.length > 11) {
                val spannableStringOffice = SpannableString(textCategory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textCategory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCategory.text = spannableStringOffice
            } else {
                holder.binding.orderItemCategory.text = textCategory
            }

            val textHistory = holder.binding.orderItemHistory.text.toString()
            if (textHistory.length > 6) {
                val spannableStringOffice = SpannableString(textHistory)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    6,
                    textHistory.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemHistory.text = spannableStringOffice
            } else {
                holder.binding.orderItemHistory.text = textHistory
            }


            val textPrice = holder.binding.orderItemPrice.text.toString()
            if (textPrice.length > 7) {
                val spannableStringOffice = SpannableString(textPrice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    7,
                    textPrice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemPrice.text = spannableStringOffice
            } else {
                holder.binding.orderItemPrice.text = textPrice
            }

            val textMarket = holder.binding.orderItemMarketname.text.toString()
            if (textMarket.length > 11) {
                val spannableStringOffice = SpannableString(textMarket)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textMarket.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketname.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketname.text = textMarket
            }
            val textOffice = holder.binding.orderItemCatdirilmaofis.text.toString()
            if (textOffice.length > 17) {
                val spannableStringOffice = SpannableString(textOffice)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    17,
                    textOffice.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilmaofis.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilmaofis.text = textOffice
            }

            val textDelivery = holder.binding.orderItemCatdirilma.text.toString()
            if (textDelivery.length > 11) {
                val spannableStringOffice = SpannableString(textDelivery)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    11,
                    textDelivery.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemCatdirilma.text = spannableStringOffice
            } else {
                holder.binding.orderItemCatdirilma.text = textDelivery
            }

            val textWeight = holder.binding.orderItemWeight.text.toString()
            if (textWeight.length > 5) {
                val spannableStringOffice = SpannableString(textWeight)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    5,
                    textWeight.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemWeight.text = spannableStringOffice
            } else {
                holder.binding.orderItemWeight.text = textWeight
            }

            val textMarketCode = holder.binding.orderItemMarketcode.text.toString()
            if (textMarketCode.length > 12) {
                val spannableStringOffice = SpannableString(textMarketCode)
                spannableStringOffice.setSpan(
                    StyleSpan(Typeface.BOLD),
                    12,
                    textMarketCode.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                holder.binding.orderItemMarketcode.text = spannableStringOffice
            } else {
                holder.binding.orderItemMarketcode.text = textMarketCode
            }
        }
    }
}