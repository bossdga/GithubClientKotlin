package com.bossdga.githubclient.source.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bossdga.githubclient.model.GitRepository

/**
 * Class that represents a Room database
 */
@Database(entities = [GitRepository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gitRepoDao(): GitRepoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "git_db").build()
    }
}