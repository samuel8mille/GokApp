package com.samuel.gokapp.model.gokrepository.service

import com.samuel.gokapp.model.gokrepository.DataModel
import com.samuel.gokapp.model.gokrepository.api.GokApi
import io.reactivex.Single

class GokApiService(private val api: GokApi) {

    fun fetchData(): Single<DataModel> {
        return api.getData()
    }
}