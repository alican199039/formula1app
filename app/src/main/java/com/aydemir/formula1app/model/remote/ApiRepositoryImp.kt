package com.aydemir.formula1app.model.remote

import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.model.data.Items
import com.aydemir.formula1app.util.extension.filterResponse
import javax.inject.Inject

class ApiRepositoryImp @Inject constructor(private val apiService: ApiService) : ApiRepository {
    override suspend fun getDrivers(): Resource<Items> {
        return apiService.getDrivers().filterResponse()
    }

    override suspend fun getDriverDetail(params: HashMap<String, Any>?): Resource<DriverDetail> {
        return apiService.getDriverDetail(params?.get("id") as Int).filterResponse()
    }
}