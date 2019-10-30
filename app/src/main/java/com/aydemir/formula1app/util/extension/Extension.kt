package com.aydemir.formula1app.util.extension

import com.aydemir.formula1app.model.core.Resource
import retrofit2.Response

fun <M : Any> Response<M>.filterResponse(): Resource<M> {

    if (this.isSuccessful) {
        this.body()?.let {
            return Resource.success("", it)
        } ?: return Resource.error("Response body is null", null)
    } else {
        return when (this.code()) {
            401 -> {
                Resource.error("401 Unauthorized", null)
            }
            in 400..501 -> {
                Resource.error("Technical error", null)
            }
            else -> {
                Resource.error("Response is not successfull", null)
            }
        }
    }
}