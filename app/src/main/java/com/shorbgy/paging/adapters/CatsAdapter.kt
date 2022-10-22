package com.shorbgy.paging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.shorbgy.paging.R
import com.shorbgy.paging.databinding.ItemCatBinding
import com.shorbgy.paging.pojo.Cat

class CatsAdapter: PagingDataAdapter<Cat, ViewHolder>(COMPARATOR){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            (holder as CatViewHolder).bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<Cat>(){
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Cat, newItem: Cat) =
                oldItem == newItem
        }
    }

    class CatViewHolder(private val binding: ItemCatBinding): ViewHolder(binding.root){
        fun bind(cat: Cat){
            binding.catImg.load(cat.url){
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }
}