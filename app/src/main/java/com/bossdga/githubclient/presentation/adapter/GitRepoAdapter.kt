package com.bossdga.githubclient.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bossdga.githubclient.OnItemClickListener
import com.bossdga.githubclient.R
import com.bossdga.githubclient.model.GitRepository

/**
 * Provide views to RecyclerView.
 */
class GitRepoAdapter(private var context: Context, private val listener: OnItemClickListener) : RecyclerView.Adapter<GitRepoAdapter.ViewHolder>() {
    private var gitRepositoryList: List<GitRepository> = listOf()

    /**
     * Provide a reference to the type of views used (custom ViewHolder)
     */
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val name: TextView = v.findViewById(R.id.Name)
        private val url: TextView = v.findViewById(R.id.Url)
        fun bind(gitRepository: GitRepository, listener: OnItemClickListener) {
            name.text = gitRepository.name
            url.text = gitRepository.url

            itemView.setOnClickListener { listener.onItemClick(gitRepository) }
        }

    }

    /**
     * Sets the data set to the recyclerview
     * @param gitRepositoryList
     */
    fun setItems(gitRepositoryList: List<GitRepository>) {
        this.gitRepositoryList = gitRepositoryList
        notifyDataSetChanged()
    }

    /**
     * Create new views (invoked by the layout manager)
     * @param viewGroup
     * @param viewType
     * @return
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_repo_data, viewGroup, false)
        return ViewHolder(v)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     * @param viewHolder
     * @param position
     */
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(gitRepositoryList[position], listener)
    }

    /**
     * Return the size of the data set (invoked by the layout manager)
     * @return
     */
    override fun getItemCount() = gitRepositoryList.size
}