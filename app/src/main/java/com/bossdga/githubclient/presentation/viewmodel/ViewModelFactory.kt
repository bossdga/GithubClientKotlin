package com.bossdga.githubclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.bossdga.githubclient.source.GitRepoRepository
import com.bossdga.githubclient.source.Injector.provideGitRepoRepository

/**
 * Factory that returns ViewModel
 */
class ViewModelFactory private constructor(private val gitRepoRepository: GitRepoRepository) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(gitRepoRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(provideGitRepoRepository(application)).also { INSTANCE = it }
            }
    }
}