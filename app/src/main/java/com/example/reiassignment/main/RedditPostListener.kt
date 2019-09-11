package com.example.reiassignment.main

import com.example.reiassignment.models.PostData

interface RedditPostListener {
    fun onPostClicked(postData: PostData)
}