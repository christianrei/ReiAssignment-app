package com.example.reiassignment.main.di

import com.example.reiassignment.main.MainPresenter
import com.example.reiassignment.main.MainViewController
import com.example.reiassignment.network.reddit.RedditRepository
import org.koin.dsl.module

val mainModule = module(override = true) {
    factory { (view: MainViewController) ->
        MainPresenter(view)
    }

    single { RedditRepository() }
}