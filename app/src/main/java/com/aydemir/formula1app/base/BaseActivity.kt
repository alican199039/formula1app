package com.aydemir.formula1app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.aydemir.formula1app.util.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel> : DaggerAppCompatActivity() {

    private lateinit var mViewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        // Set ViewModel
        mViewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass().java)
        // Initialize UI
        prepareView(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): KClass<VM>

    fun getViewModel(): VM {
        return mViewModel
    }

    abstract fun prepareView(savedInstanceState: Bundle?)

}