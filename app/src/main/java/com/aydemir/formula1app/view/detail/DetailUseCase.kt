package com.aydemir.formula1app.view.detail

import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.core.UseCase
import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.model.remote.ApiRepositoryImp
import javax.inject.Inject

class DetailUseCase @Inject constructor(private val apiRepositoryImp: ApiRepositoryImp) :
    UseCase<Resource<DriverDetail>, HashMap<String, Any>?>() {
    override suspend fun buildRequest(params: HashMap<String, Any>?): Resource<DriverDetail> {
        return apiRepositoryImp.getDriverDetail(params)
    }
}