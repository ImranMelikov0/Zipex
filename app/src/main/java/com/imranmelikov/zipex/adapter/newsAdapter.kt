package com.imranmelikov.zipex.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.imranmelikov.zipex.databinding.HomeItemRowBinding
import com.imranmelikov.zipex.model.News
import com.imranmelikov.zipex.view.HomeFragmentDirections
import javax.inject.Inject

class newsAdapter @Inject constructor(
  private val glide:RequestManager
):RecyclerView.Adapter<newsAdapter.newsViewHolder>() {
    class newsViewHolder(var binding:HomeItemRowBinding):RecyclerView.ViewHolder(binding.root)

   private val diffUtil=object :DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem==newItem
        }

    }
   private val recyclerDiffer=AsyncListDiffer(this,diffUtil)
    var newsList:List<News>
        get() = recyclerDiffer.currentList
        set(value) = recyclerDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val binding=HomeItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return newsViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return newsList.size
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val newsArrayList=newsList.get(position)
        holder.binding.title.text=newsArrayList.title
        holder.binding.text.text=newsArrayList.post
        glide.load(newsArrayList.imageUrl)
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
           val newsId= newsArrayList.uuid!!
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(newsId))
        }
    }
}