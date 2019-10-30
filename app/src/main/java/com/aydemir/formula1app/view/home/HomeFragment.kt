package com.aydemir.formula1app.view.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aydemir.formula1app.R
import com.aydemir.formula1app.base.BaseFragment
import com.aydemir.formula1app.model.data.Driver
import com.aydemir.formula1app.util.NetworkState
import com.aydemir.formula1app.util.extension.setGone
import com.aydemir.formula1app.util.extension.setVisible
import com.aydemir.formula1app.util.helper.FavoriteHelper
import com.aydemir.formula1app.view.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    private lateinit var driverListAdapter: DriverListAdapter

    @Inject
    internal lateinit var favoriteHelper: FavoriteHelper

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        init()
        registerHandlers()
        initObservers()
        initAdapters()
    }

    private fun init() {}

    private fun registerHandlers() {

    }

    private fun initObservers() {
        getViewModel().networkStatus?.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    progress.setVisible()
                }
                NetworkState.LOADED -> {
                    progress.setGone()
                }
                NetworkState.FAILED -> {
                    progress.setGone()
                }
            }
        })
        getViewModel().driverList?.observe(viewLifecycleOwner, Observer {
            driverListAdapter.addList(it)
        })
    }

    private fun initAdapters() {
        recycler_home.let {
            driverListAdapter =
                DriverListAdapter(favoriteHelper, mutableListOf()) { data, type ->
                    recyclerViewClick(
                        data,
                        type
                    )
                }
            val lm = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

            it.layoutManager = lm
            it.adapter = driverListAdapter
        }
    }

    //--------------------------------------------------------------------------------------------//

    private fun recyclerViewClick(data: Any, type: String) {
        fragmentCallBack?.startFragment(

            DetailFragment.newInstance(data as Driver),
            addStack = true, animated = true
        )
    }

    //--------------------------------------------------------------------------------------------//
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
