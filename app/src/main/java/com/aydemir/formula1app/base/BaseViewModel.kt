package com.aydemir.formula1app.base

import androidx.lifecycle.ViewModel
import com.aydemir.formula1app.util.NetworkState
import com.aydemir.formula1app.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val networkStatus: SingleLiveEvent<NetworkState>?
    val message: SingleLiveEvent<String>?
    val status: SingleLiveEvent<Boolean>?

    init {
        status = SingleLiveEvent()
        networkStatus = SingleLiveEvent()
        message = SingleLiveEvent()
    }
}