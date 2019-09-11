package com.example.reiassignment.postviewer

import com.example.reiassignment.models.PostData

class PostViewerPresenter(private val view: PostViewerViewController) {

    fun handleData(postData: PostData?) {
        postData?.let { it ->
            view.setupTitle(it.title)
            when {
                it.selfText.isNotEmpty() -> view.setupBody(it.selfText)
                it.url.isNotEmpty() -> view.showWebView(it.url)
                else -> view.showEmptyView()
            }
            if (it.thumbnail.isEmpty()) {
                view.hideThumbnail()
            } else {
                view.showThumbnail(it.thumbnail)
            }
        } ?: view.showEmptyView()
    }

}