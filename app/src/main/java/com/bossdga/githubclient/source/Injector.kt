package com.bossdga.githubclient.source

import android.app.Application
import com.bossdga.githubclient.source.network.RetrofitService
import com.bossdga.githubclient.source.persistence.AppDatabase

/**
 * Class responsible of injecting repositories to a view model factory
 */
object Injector {
    /**
     * Provides a github repository
     * @param application
     * @return
     */
    @JvmStatic
    fun provideGitRepoRepository(application: Application): GitRepoRepository {
        val db = AppDatabase.getInstance(application)
        return GitRepoRepository(db.gitRepoDao(), RetrofitService.gitRepoAPI)
    }
}