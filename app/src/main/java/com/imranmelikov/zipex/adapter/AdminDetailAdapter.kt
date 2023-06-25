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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.imranmelikov.zipex.R
import com.imranmelikov.zipex.databinding.AdmindetailRowBinding
import com.imranmelikov.zipex.model.Order1
import com.imranmelikov.zipex.model.Order2
import com.imranmelikov.zipex.model.Order3
import com.imranmelikov.zipex.model.Order4
import com.imranmelikov.zipex.model.Order5
import com.imranmelikov.zipex.model.Order6
import com.imranmelikov.zipex.model.Order7
import com.imranmelikov.zipex.model.Order8
import com.imranmelikov.zipex.model.Order9
import com.imranmelikov.zipex.view.AdminDetailFragmentDirections
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class AdminDetailAdapter @Inject constructor():RecyclerView.Adapter<AdminDetailAdapter.AdminDetailViewHolder>() {
    class AdminDetailViewHolder(var binding:AdmindetailRowBinding):RecyclerView.ViewHolder(binding.root)
     var showFirst:Int=1

    var onItemClickOrder2Add:((Order1)->Unit)?=null
    var onItemClickOrderDelete:((Order1)->Unit)?=null

    var onItemClickOrder3Add:((Order3)->Unit)?=null
    var onItemClickOrder3AddDelete:((Order2)->Unit)?=null
    var onItemClickOrder2Delete:((Order2)->Unit)?=null

    var onItemClickOrder4Add:((Order3)->Unit)?=null
    var onItemClickOrder3Delete:((Order3)->Unit)?=null

    var onItemClickOrder5Add:((Order4)->Unit)?=null
    var onItemClickOrder4Delete:((Order4)->Unit)?=null

    var onItemClickOrder6Add:((Order5)->Unit)?=null
    var onItemClickOrder5Delete:((Order5)->Unit)?=null

    var onItemClickOrder7Add:((Order6)->Unit)?=null
    var onItemClickOrder6Delete:((Order6)->Unit)?=null

    var onItemClickOrder8Add:((Order7)->Unit)?=null
    var onItemClickOrder7Delete:((Order7)->Unit)?=null

    var onItemClickOrder9Add:((Order8)->Unit)?=null
    var onItemClickOrder8Delete:((Order8)->Unit)?=null

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminDetailViewHolder {
        val binding=AdmindetailRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdminDetailViewHolder(binding)
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

    override fun onBindViewHolder(holder: AdminDetailViewHolder, position: Int) {
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE
            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                   onItemClickOrder2Add?.let {
                       it(order1ArrayList)
                   }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrderDelete?.let {
                        it(order1ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    if (holder.binding.catdirilma.text.toString().isEmpty()||holder.binding.ceki.text.toString().isEmpty()){
                        Toast.makeText(holder.itemView.context,"Məlumatları daxil edin",Toast.LENGTH_SHORT).show()
                    }else{
                        val currentDate = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
                        val formatDate = currentDate.format(formatter)
                        val order3=Order3(order2ArrayList.url,order2ArrayList.category,order2ArrayList.count,order2ArrayList.color,order2ArrayList.size,order2ArrayList.price,order2ArrayList.comment,formatDate,order2ArrayList.country,order2ArrayList.sigorta,order2ArrayList.payment,order2ArrayList.marketName,order2ArrayList.marketCode,order2ArrayList.office,holder.binding.catdirilma.text.toString(),holder.binding.ceki.text.toString())
                        order3.uuid=order2ArrayList.uuid
                        onItemClickOrder3Add?.let {
                            it(order3)
                        }
                        onItemClickOrder3AddDelete?.let {
                            it(order2ArrayList)
                        }
                        Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()
                    }
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder2Delete?.let {
                        it(order2ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder4Add?.let {
                        it(order3ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder3Delete?.let {
                        it(order3ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder5Add?.let {
                        it(order4ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder4Delete?.let {
                        it(order4ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder6Add?.let {
                        it(order5ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder5Delete?.let {
                        it(order5ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder7Add?.let {
                        it(order6ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder6Delete?.let {
                        it(order6ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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


            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder8Add?.let {
                        it(order7ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder7Delete?.let {
                        it(order7ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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

            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE

            holder.binding.button.setOnClickListener {
                val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.alert_dialog_confirm,null)
                val no=dialogView.findViewById<Button>(R.id.no)
                val yes=dialogView.findViewById<Button>(R.id.yes)

                val alertDialogBuilder= AlertDialog.Builder(holder.itemView.context)
                alertDialogBuilder.setView(dialogView)

                val alertDialog=alertDialogBuilder.create()
                no.setOnClickListener {
                    alertDialog.dismiss()
                }
                yes.setOnClickListener {
                    onItemClickOrder9Add?.let {
                        it(order8ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama təsdiq olundu",Toast.LENGTH_SHORT).show()
                    alertDialog.dismiss()
                }
                alertDialog.show()

            }
            holder.binding.button3.setOnClickListener {
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
                    onItemClickOrder8Delete?.let {
                        it(order8ArrayList)
                    }
                    Toast.makeText(holder.itemView.context,"Bağlama silindi",Toast.LENGTH_SHORT).show()
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

            holder.binding.catdirilma.visibility= View.GONE
            holder.binding.ceki.visibility= View.GONE
            holder.binding.cekitopedittext.visibility=View.GONE
            holder.binding.catdirilmatopedittext.visibility=View.GONE
            holder.binding.button.visibility=View.GONE
            holder.binding.button3.visibility=View.GONE


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