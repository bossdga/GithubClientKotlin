package com.bossdga.githubclient.presentation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bossdga.githubclient.OnItemClickListener
import com.bossdga.githubclient.R
import com.bossdga.githubclient.model.GitRepository
import com.bossdga.githubclient.presentation.adapter.GitRepoAdapter
import com.bossdga.githubclient.presentation.viewmodel.MainViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * Fragment that will show a list of github repositories
 */
class MainFragment : BaseFragment() {
    private lateinit var adapter: GitRepoAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        mSwipeRefreshLayout = rootView.findViewById(R.id.SwipeRefreshLayout)
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener)
        mRecyclerView = rootView.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = GitRepoAdapter(activity as Context, object : OnItemClickListener {
            override fun onItemClick(content: GitRepository) {
                // Do some stuff, like going to repo details screen
            }
        })

        mRecyclerView.adapter = adapter

        loadContent()

        return rootView
    }

    /**
     * Listener for swipe to refresh functionality
     */
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.dispose()
    }

    /**
     * Method that adds a Disposable to the CompositeDisposable
     * @param gitReposObservable
     */
    private fun subscribeGitRepos(gitReposObservable: Observable<List<GitRepository>>) {
        disposable.add(gitReposObservable
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<GitRepository>>() {
                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onNext(gitRepositoryList: List<GitRepository>) {
                    adapter.setItems(gitRepositoryList)
                    mSwipeRefreshLayout.isRefreshing = false
                }
            }))
    }

    override fun loadContent() {
        subscribeGitRepos(mainViewModel.getRepositories("bossdga"))
    }
}