package com.example.reiassignment.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    const val REDDIT_KOTLIN_URL = "https://www.reddit.com/r/"
    // kotlin/.json

    private const val TIMEOUT_VALUE_SECONDS = 30L

    private val gsonConverterFactory = GsonConverterFactory.create()
    private val callAdapterFactory = RxJava2CallAdapterFactory.create()

    val okHttpClient: OkHttpClient by lazy {
        val okhttpClientBuilder = OkHttpClient.Builder()
        okhttpClientBuilder.connectTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        okhttpClientBuilder.readTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)
        okhttpClientBuilder.writeTimeout(TIMEOUT_VALUE_SECONDS, TimeUnit.SECONDS)

        okhttpClientBuilder.build()
    }

    val api: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REDDIT_KOTLIN_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }

}