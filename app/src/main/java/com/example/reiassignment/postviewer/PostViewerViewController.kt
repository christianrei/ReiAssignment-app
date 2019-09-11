package com.example.reiassignment.postviewer

interface PostViewerViewController {
    fun setupTitle(title: String)
    fun showThumbnail(thumbnail: String)
    fun hideThumbnail()
    fun setupBody(selfText: String)
    fun showWebView(url: String)
    fun showEmptyView()
}