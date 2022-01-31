package com.example.mvvmglobalnewsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmglobalnewsapp.R
import com.example.mvvmglobalnewsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    lateinit var articlesList: MutableList<Article>

    // Adapter
    private lateinit var mListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    // Differ
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    var differ = AsyncListDiffer(this, differCallback)


    inner class ArticleViewHolder(itemView: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        var titleTV: TextView = itemView.findViewById(R.id.title_card)
        var sourceNameTV: TextView = itemView.findViewById(R.id.sourceName_card)
        var descriptionTV: TextView = itemView.findViewById(R.id.description_card)
        var thumbnailIV: ImageView = itemView.findViewById(R.id.thumbnail_image_card)
        var authorTV: TextView = itemView.findViewById(R.id.author_card)
        var publishedAtTV: TextView = itemView.findViewById(R.id.publishedAt_card)

        // Adapter Initialization
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_card_item,
                parent,
                false
            ), mListener
        )
    }
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        articlesList = differ.currentList
        val article = articlesList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holder.thumbnailIV)
            holder.titleTV.text = article.title
            holder.sourceNameTV.text = article.source.name
            holder.authorTV.text = article.author
            holder.publishedAtTV.text = article.publishedAt
            holder.descriptionTV.text = article.description
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}






















