package com.bossdga.githubclient.source

import com.bossdga.githubclient.model.GitRepository
import com.bossdga.githubclient.source.network.api.GitRepoAPI
import com.bossdga.githubclient.source.persistence.GitRepoDao
import io.reactivex.Observable

/**
 * Repository to execute database and network operations
 */
class GitRepoRepository(private val dao: GitRepoDao, private val api: GitRepoAPI) {

    /**
     * Gets a list of git repositories
     * @param userName
     * @return
     */
    fun getRepositories(userName: String): Observable<List<GitRepository>> {
        return api.getRepositories(userName)
        // TODO Add local database access in case there is not network connectivity or for caching purposes
        //return this.dao.getRepositories(userName);
    }

}