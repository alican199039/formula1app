package com.aydemir.formula1app.view.home

import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.core.UseCase
import com.aydemir.formula1app.model.data.Items
import com.aydemir.formula1app.model.remote.ApiRepositoryImp
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val apiRepositoryImp: ApiRepositoryImp) :
    UseCase<Resource<Items>, HashMap<String, Any>?>() {
    override suspend fun buildRequest(params: HashMap<String, Any>?): Resource<Items> {
        return apiRepositoryImp.getDrivers()
    }
}