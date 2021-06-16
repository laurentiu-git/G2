package com.rld.g2esports.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rld.g2esports.R
import javax.inject.Inject

class ShopDetailsAdapter @Inject constructor() : RecyclerView.Adapter<ShopDetailsAdapter.TestAdapterViewHolder>() {

    inner class TestAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapterViewHolder {
        return TestAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_shop_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestAdapterViewHolder, position: Int) {
        holder.itemView.apply {
            val imageView = findViewById<ImageView>(R.id.imageView)

          Glide.with(context).load(differ.currentList[position]).into(imageView)
            setOnClickListener {
                onItemClickListener?.let { it(differ.currentList[position]) }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }




    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
