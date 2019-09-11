package com.example.reiassignment.postviewer.di

import com.example.reiassignment.postviewer.PostViewerPresenter
import com.example.reiassignment.postviewer.PostViewerViewController
import org.koin.dsl.module

val postViewerModule = module(override = true) {
    factory { (view: PostViewerViewController) ->
        PostViewerPresenter(view)
    }
}