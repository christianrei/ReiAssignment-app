package com.example.reiassignment.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reiassignment.models.PostData

class RedditAdapter(
    private val postData: List<PostData>,
    private val postListener: RedditPostListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.get(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(postData[position]) {
            postListener.onPostClicked(postData[position])
        }
    }

    override fun getItemCount(): Int {
        return postData.size
    }

}