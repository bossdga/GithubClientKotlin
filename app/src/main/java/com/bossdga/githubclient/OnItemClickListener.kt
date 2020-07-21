package com.bossdga.githubclient

import com.bossdga.githubclient.model.GitRepository

interface OnItemClickListener {
    fun onItemClick(gitRepository: GitRepository)
}