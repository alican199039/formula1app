package com.aydemir.formula1app.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aydemir.formula1app.base.BaseViewModel
import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.data.Driver
import com.aydemir.formula1app.util.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : BaseViewModel() {

    val driverList: MutableLiveData<MutableList<Driver>>?

    init {
        driverList = MutableLiveData()
        getDriverList()
    }

    private fun getDriverList() {
        networkStatus?.value= NetworkState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val resource = homeUseCase.execute(hashMapOf())
            withContext(Dispatchers.Main) {
                if (resource.status == Resource.Status.SUCCESS) {
                    driverList?.value = resource.data?.items
                    Timber.e("Okhttp --> Data SUCCESS")
                } else {
                    Timber.e("Okhttp --> Data ERROR")
                }
                networkStatus?.value= NetworkState.LOADED
                Timber.e("Okhttp --> Data LOADED ")
            }
        }
    }
}