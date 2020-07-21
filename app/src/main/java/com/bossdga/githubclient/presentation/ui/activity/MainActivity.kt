package com.bossdga.githubclient.presentation.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bossdga.githubclient.R
import com.bossdga.githubclient.presentation.viewmodel.BaseViewModel
import com.bossdga.githubclient.presentation.viewmodel.MainViewModel

/**
 * Main Activity that holds several fragments
 */
class MainActivity : BaseActivity<BaseViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpActionBar(R.string.app_name, false)
    }

    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

}