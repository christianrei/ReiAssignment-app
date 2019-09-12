package com.example.reiassignment.main

import android.annotation.SuppressLint
import com.example.reiassignment.models.PostData
import com.example.reiassignment.network.reddit.RedditRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(private val view: MainViewController) : KoinComponent {

    companion object {
        private const val KOTLIN_SUBREDDIT_NAME = "kotlin"
    }

    private val repository: RedditRepository by inject()
    private val compositeDisposable = CompositeDisposable()

    fun getSubredditData() {
        view.showLoadingView()
        compositeDisposable.add(
            repository.getSubredditData(KOTLIN_SUBREDDIT_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ redditData ->
                    val postDataList = redditData.data.children.map {
                        PostData(
                            it.data.id,
                            it.data.author,
                            it.data.score,
                            it.data.url,
                            it.data.selftext,
                            it.data.num_comments,
                            it.data.thumbnail,
                            it.data.title
                        )
                    }
                    view.setAdapter(postDataList)
                    view.hideLoadingView()
                }, {
                    view.showErrorView()
                    view.hideLoadingView()
                })
        )
    }

    fun tryAgainClicked() {
        getSubredditData()
    }

    fun onPostClicked(postData: PostData) {
        view.launchPostViewerScreen(postData)
    }

    fun onStop() {
        compositeDisposable.clear()
    }

}