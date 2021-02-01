package com.example.wens.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wens.R
import com.example.wens.databinding.HomeListItemBinding
import com.example.wens.model.objects.Articles

class HomeFeedAdapter(
    val context: Context,
    var articles: MutableList<Articles>,
    val clickListener: (Articles, HomeListItemBinding) -> Unit
) :
    RecyclerView.Adapter<HomeFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeListItemBinding
                .inflate(
                    LayoutInflater
                        .from(context), parent, false
                )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position], clickListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun setData(refreshedList: List<Articles>) {
        refreshedList.forEach {
            articles.add(it)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Articles, clickListener: (Articles, HomeListItemBinding) -> Unit) {
            binding.apply {
                tvFeedHeadline.apply {
                    text = article.title
                    transitionName = article.title
                }
                tvArticleSnippet.apply {
                    text = article.description
                    transitionName = article.description
                }
                tvPublicationName.apply {
                    text = article.source?.name
                    transitionName = article.source?.name
                }
                ivNewsFeedImage.apply {
                    load(article.urlToImage) {
                        placeholder(R.drawable.ic_placeholder_)
                        crossfade(true)
                        crossfade(100)
                        error(R.drawable.ic_placeholder_)
                    }
                    transitionName = article.urlToImage
                }
            }
            itemView.setOnClickListener { clickListener(article, binding) }
        }
    }
}