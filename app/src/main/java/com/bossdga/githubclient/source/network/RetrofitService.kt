package com.bossdga.githubclient.source.network

import com.bossdga.githubclient.source.network.api.GitRepoAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * API Client that provides access to a single instance of the Service
 */
object RetrofitService {
    private const val BASE_URL = "https://api.github.com/"

    private fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val gitRepoAPI: GitRepoAPI by lazy {
        retrofitService().create(GitRepoAPI::class.java)
    }
}