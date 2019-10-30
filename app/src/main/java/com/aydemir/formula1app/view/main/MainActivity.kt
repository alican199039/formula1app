package com.aydemir.formula1app.view.main

import android.os.Bundle
import com.aydemir.formula1app.R
import com.aydemir.formula1app.base.BaseActivity
import com.aydemir.formula1app.base.BaseFragment
import com.aydemir.formula1app.util.extension.pushFragmentToContainer
import com.aydemir.formula1app.util.listener.FragmentCallBack
import com.aydemir.formula1app.view.home.HomeFragment
import kotlin.reflect.KClass

class MainActivity : BaseActivity<MainViewModel>(), FragmentCallBack {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        pushFragmentToContainer(HomeFragment.newInstance(), addToStack = false,animated = false)
    }

    override fun startFragment(fragment: BaseFragment<*>, addStack: Boolean,animated:Boolean) {
        pushFragmentToContainer(fragment, addStack,animated)
    }
}
