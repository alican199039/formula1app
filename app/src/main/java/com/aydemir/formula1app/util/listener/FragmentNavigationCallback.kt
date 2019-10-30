package com.aydemir.formula1app.util.listener

import com.aydemir.formula1app.base.BaseFragment

interface FragmentCallBack {
    fun startFragment(fragment: BaseFragment<*>,addStack:Boolean,animated:Boolean)
}