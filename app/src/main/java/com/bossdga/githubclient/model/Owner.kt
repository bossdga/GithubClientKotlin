package com.bossdga.githubclient.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Class that represents a repo owner
 */
data class Owner(@Expose @SerializedName("id") var id: Int,
                 @Expose @SerializedName("login") var login: String) {}