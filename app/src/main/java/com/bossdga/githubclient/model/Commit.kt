package com.bossdga.githubclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Class that represents a commit
 */
data class Commit(@Expose @SerializedName("id") var id: Int) {}