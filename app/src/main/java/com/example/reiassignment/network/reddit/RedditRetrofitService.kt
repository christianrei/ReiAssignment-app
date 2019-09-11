package com.example.reiassignment.network.reddit

import com.example.reiassignment.models.RedditData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RedditRetrofitService {

    @Headers("Accept: application/json")
    @GET("{subreddit}/.json")
    fun getData(@Path("subreddit") subreddit: String): Single<RedditData>

}