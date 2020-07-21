package com.bossdga.githubclient.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Class that represents a repository
 */
@Entity(tableName = "git_repo")
data class GitRepository (@PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") var id: Int,
                          @SerializedName("name") @ColumnInfo(name = "name") var name: String,
                          @SerializedName("html_url") @ColumnInfo(name = "html_url") var url: String) {}