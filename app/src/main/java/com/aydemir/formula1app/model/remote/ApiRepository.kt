package com.aydemir.formula1app.model.remote

import com.aydemir.formula1app.model.core.Resource
import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.model.data.Items

interface ApiRepository {
    suspend fun getDrivers(): Resource<Items>
    suspend fun getDriverDetail(params: HashMap<String, Any>?): Resource<DriverDetail>
}