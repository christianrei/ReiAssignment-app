package com.example.reiassignment.models

data class RedditData(
    val `data`: Data,
    val kind: String
)

data class Data(
    val children: List<Children>,
    val dist: Int
)

data class Children(
    val `data`: ChildData,
    val kind: String
)

data class ChildData(
    val author: String,
    val id: String,
    val name: String,
    val num_comments: Int,
    val score: Int,
    val selftext: String,
    val subreddit: String,
    val thumbnail: String,
    val title: String,
    val url: String
)