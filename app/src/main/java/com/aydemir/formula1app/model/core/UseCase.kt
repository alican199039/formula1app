package com.aydemir.formula1app.model.core

import androidx.annotation.Nullable

@Suppress("UNCHECKED_CAST")
abstract class UseCase<M, Params> {

    abstract suspend fun buildRequest(@Nullable params: Params?): M

    suspend fun execute(@Nullable params: Params?): M {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            Resource.error(exception.message!!, null) as M
        }
    }

}