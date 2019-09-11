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
    val archived: Boolean,
    val author: String,
    val author_fullname: String,
    val category: Any,
    val clicked: Boolean,
    val downs: Int,
    val edited: Boolean,
    val gilded: Int,
    val id: String,
    val is_self: Boolean,
    val likes: Any,
    val locked: Boolean,
    val name: String,
    val num_comments: Int,
    val over_18: Boolean,
    val score: Int,
    val selftext: String,
    val spoiler: Boolean,
    val subreddit: String,
    val thumbnail: String,
    val title: String,
    val ups: Int,
    val url: String
)