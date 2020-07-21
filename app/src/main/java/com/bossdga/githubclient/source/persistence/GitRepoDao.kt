package com.bossdga.githubclient.source.persistence

import androidx.room.Dao
import androidx.room.Query
import com.bossdga.githubclient.model.GitRepository
import io.reactivex.Observable

/**
 * Dao to interact with the database
 */
@Dao
interface GitRepoDao {
    @Query("SELECT * FROM git_repo")
    fun getRepositories(): Observable<List<GitRepository>>
}