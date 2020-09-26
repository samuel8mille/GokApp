package com.samuel.gokapp.model.gokrepository.api

import com.samuel.gokapp.model.gokrepository.DataModel
import io.reactivex.Single
import retrofit2.http.GET

interface GokApi {

    @GET("products")
    fun getData(): Single<DataModel>
}