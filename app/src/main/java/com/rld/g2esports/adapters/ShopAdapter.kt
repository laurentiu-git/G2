package com.rld.g2esports.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rld.g2esports.R
import com.rld.g2esports.data.models.shop.ShopResponseItem
import javax.inject.Inject

class ShopAdapter @Inject constructor() : RecyclerView.Adapter<ShopAdapter.TestAdapterViewHolder>() {

    inner class TestAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<ShopResponseItem>() {
        override fun areItemsTheSame(oldItem: ShopResponseItem, newItem: ShopResponseItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ShopResponseItem, newItem: ShopResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapterViewHolder {
        return TestAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestAdapterViewHolder, position: Int) {
     val shopResponseItem = differ.currentList[position]
        holder.itemView.apply {
            val title = findViewById<TextView>(R.id.title)
            val description = findViewById<TextView>(R.id.description)
            val price = findViewById<TextView>(R.id.price)
            val imageView = findViewById<ImageView>(R.id.imageView)

            val priceText = differ.currentList[position].price
            price.text = String.format("$priceText €")
            title.text = differ.currentList[position].name
            val uncutDescription = differ.currentList[position].description
            val descriptionText = uncutDescription.substringAfter("<p>").substringBefore("</p>")
            description.text = descriptionText

          Glide.with(context).load(differ.currentList[position].images.get(0).src).into(imageView)

            setOnClickListener {
                onItemClickListener?.let { it(shopResponseItem) }
            }
        }
    }

    private var onItemClickListener: ((ShopResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ShopResponseItem) -> Unit) {
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
