package com.bossdga.githubclient.source.network.api

import com.bossdga.githubclient.model.GitRepository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interface that defines the API requests
 */
interface GitRepoAPI {
    /**
     * Gets a list of git repositories
     * @param userName
     * @return
     */
    @GET("/users/{user_name}/repos")
    fun getRepositories(@Path("user_name") userName: String): Observable<List<GitRepository>>
}