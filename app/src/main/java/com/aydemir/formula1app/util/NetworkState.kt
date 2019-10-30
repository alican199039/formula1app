package com.aydemir.formula1app.util

import androidx.annotation.Nullable

class NetworkState(val status: Status, @Nullable message: String?) : Exception(message) {

    constructor(status: Status, message: String?, errorCode: Int) : this(status, message)

    enum class Status {
        LOADING,
        SUCCESS,
        FAILED
    }

    companion object {
        val LOADED = NetworkState(
            Status.SUCCESS,
            ""
        )
        val LOADING = NetworkState(
            Status.LOADING,
            ""
        )
        val FAILED = NetworkState(
            Status.FAILED,
            ""
            , 0
        )
    }
}