package com.bossdga.githubclient.presentation.ui.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.bossdga.githubclient.ProgressDialogHandler
import com.bossdga.githubclient.R
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment : Fragment(), ProgressDialogHandler {
    protected lateinit var mProgressDialog: ProgressDialog
    protected var showDialog = false
    protected var disposable = CompositeDisposable()
    protected var extras: Intent? = null

    abstract fun loadContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        extras = requireActivity().intent
    }

    override fun onPause() {
        super.onPause()

        showDialog = false
    }

    /**
     * Creates and shows a ProgressDialog
     */
    override fun showProgressDialog() {
        showDialog = true

        Handler().postDelayed({
            if (showDialog) {
                mProgressDialog = ProgressDialog(activity)
                mProgressDialog.setTitle(R.string.please_wait)
                mProgressDialog.setMessage(resources.getString(R.string.loading))
                mProgressDialog.show()
            }
        }, 1)
    }

    /**
     * Hides the ProgressDialog
     */
    override fun hideProgressDialog() {
        showDialog = false
        mProgressDialog.dismiss()
    }

}