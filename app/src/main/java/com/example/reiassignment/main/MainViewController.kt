package com.example.reiassignment.main

import com.example.reiassignment.models.PostData

interface MainViewController {
    fun setAdapter(postData: List<PostData>)
    fun showErrorView()
    fun launchPostViewerScreen(postData: PostData)
    fun showLoadingView()
    fun hideLoadingView()
}