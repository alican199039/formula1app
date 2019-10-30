package com.aydemir.formula1app.view.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import coil.api.load
import com.aydemir.formula1app.R
import com.aydemir.formula1app.base.BaseFragment
import com.aydemir.formula1app.model.data.Driver
import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.util.NetworkState
import com.aydemir.formula1app.util.extension.setGone
import com.aydemir.formula1app.util.extension.setVisible
import kotlinx.android.synthetic.main.fragment_detail.*

private const val ARG_PARAM1 = "param_driver"

class DetailFragment : BaseFragment<DetailViewModel>() {
    private var driver: Driver? = null

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        initObservers()
        getDetail()
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
        getViewModel().driverDetail?.observe(viewLifecycleOwner, Observer {
            setUi(it)
        })
    }

    private fun setUi(driverDetail: DriverDetail) {
        driverDetail.apply {
            txt_driver_age.text = age.toString()
            txt_driver_name.text = driver?.name
            txt_driver_team.text = team
            img_driver_image.load(image) {
                crossfade(true)
            }
        }
    }

    private fun getDetail() {
        driver?.id?.let { getViewModel().getDriverDetail(it) }
    }
    //--------------------------------------------------------------------------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            driver = it.getParcelable(ARG_PARAM1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(driver: Driver) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, driver)
                }
            }
    }
}
