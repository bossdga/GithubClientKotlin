package com.bossdga.githubclient.presentation.viewmodel

import com.bossdga.githubclient.model.GitRepository
import com.bossdga.githubclient.source.GitRepoRepository
import io.reactivex.Observable

/**
 * ViewModel used with the MainActivity
 */
class MainViewModel(private val gitRepoRepository: GitRepoRepository) : BaseViewModel() {

    /**
     * Method that returns an Observable of a Collection of git repositories
     * @return
     */
    fun getRepositories(userName: String): Observable<List<GitRepository>> {
        return gitRepoRepository.getRepositories(userName)
    }

}