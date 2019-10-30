package com.aydemir.formula1app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aydemir.formula1app.util.listener.FragmentCallBack
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

    var fragmentCallBack: FragmentCallBack? = null

    private lateinit var mViewModel: VM

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun getViewModelClass(): Class<VM>

    protected abstract fun getLayoutId(): Int

    fun getViewModel(): VM {
        return mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareView(savedInstanceState)
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentCallBack) {
            fragmentCallBack = context
        } else {
            throw RuntimeException("$context must implement fragmentCallBack")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentCallBack = null
    }
}

