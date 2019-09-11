package com.example.reiassignment.network.reddit

import com.example.reiassignment.models.RedditData
import com.example.reiassignment.network.RetrofitService
import io.reactivex.Single

class RedditRepository {

    private val service by lazy {
        RetrofitService.api.create<RedditRetrofitService>(RedditRetrofitService::class.java)
    }

    fun getSubredditData(subreddit: String): Single<RedditData> {
        return service.getData(subreddit)
    }

}