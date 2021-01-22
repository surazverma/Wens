package com.example.wens.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.wens.R
import com.example.wens.model.objects.Articles

class HomeFeedAdapter(val context: Context, var articles: MutableList<Articles>) :
    RecyclerView.Adapter<HomeFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.home_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position], context)
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvArticleTitle)
        val tvPublicationName: TextView = view.findViewById(R.id.tvPublicationName)
        val tvArticleBody: TextView = view.findViewById(R.id.tvArticleSnippet)
        val ivFeedImage: ImageView = view.findViewById(R.id.ivNewsFeedImage)

        fun bind(article: Articles, context: Context) {
            tvTitle.text = article.title
            tvArticleBody.text = article.description
            tvPublicationName.text = article.source.name
            ivFeedImage.load(article.urlToImage) {
                placeholder(R.drawable.ic_placeholder_)
                crossfade(true)
                crossfade(100)
                error(R.drawable.ic_placeholder_)
            }

//            Glide.with(context)
//                .load(article.urlToImage)
//                .placeholder(R.drawable.ic_news_feed_placeholder)
//                .into(ivFeedImage)
        }
    }
}