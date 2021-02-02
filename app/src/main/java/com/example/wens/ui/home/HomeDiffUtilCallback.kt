package com.example.wens.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.wens.model.objects.Articles

object HomeDiffUtilCallback : DiffUtil.ItemCallback<Articles>() {
    override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
        return oldItem.content == newItem.content
    }
}