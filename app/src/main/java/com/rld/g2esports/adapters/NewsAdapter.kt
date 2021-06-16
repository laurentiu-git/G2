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
import com.rld.g2esports.data.models.news.NewsResponseItem
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.TestAdapterViewHolder>() {

    inner class TestAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<NewsResponseItem>() {
        override fun areItemsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsResponseItem, newItem: NewsResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapterViewHolder {
        return TestAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TestAdapterViewHolder, position: Int) {
        holder.itemView.apply {
            val imageView = findViewById<ImageView>(R.id.imageView)
            val title = findViewById<TextView>(R.id.title)
            val description = findViewById<TextView>(R.id.description)

            title.text = differ.currentList[position].title.rendered
            description.text = differ.currentList[position].excerpt.rendered.substringAfter("<p>").substringBefore("</p>")

      //    Glide.with(context).load(differ.currentList[position].link).into(imageView)

            setOnClickListener {
                onItemClickListener?.let { it(differ.currentList[position].link) }
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
