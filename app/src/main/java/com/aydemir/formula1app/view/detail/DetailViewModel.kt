package com.aydemir.formula1app.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aydemir.formula1app.base.BaseViewModel
import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.util.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val detailUseCase: DetailUseCase) :
    BaseViewModel() {
    val driverDetail: MutableLiveData<DriverDetail>?

    init {
        driverDetail = MutableLiveData()
    }

    fun getDriverDetail(id: Int) {
        networkStatus?.value= NetworkState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val resource = detailUseCase.execute(hashMapOf("id" to id))
            withContext(Dispatchers.Main) {
                if (resource.status == Resource.Status.SUCCESS) {
                    driverDetail?.value = resource.data
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