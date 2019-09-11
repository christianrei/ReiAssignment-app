package com.example.reiassignment.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reiassignment.R
import com.example.reiassignment.extensions.hide
import com.example.reiassignment.extensions.show
import com.example.reiassignment.models.PostData
import kotlinx.android.synthetic.main.reddit_post_view.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun bind(postData: PostData, onPostClickListener: () -> Unit) {
        itemView.tv_post_score.text = postData.score.toString()
        itemView.tv_post_title.text = postData.title
        itemView.tv_post_num_comments.text =
            "${postData.numComments} ${itemView.context.getString(R.string.comments)}"
        itemView.tv_post_author.text = postData.author
        if (postData.thumbnail.isEmpty()) {
            itemView.iv_post_thumbnail.hide()
        } else {
            itemView.iv_post_thumbnail.show()
            Glide.with(itemView.context).load(postData.thumbnail).into(itemView.iv_post_thumbnail)
        }

        itemView.setOnClickListener { onPostClickListener() }
    }

    companion object {
        fun get(parent: ViewGroup): PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.reddit_post_view,
                    parent,
                    false
                )
            )
        }
    }

}