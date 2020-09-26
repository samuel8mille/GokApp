package com.samuel.gokapp.di

import com.samuel.gokapp.GokConstants.Companion.BASE_URL
import com.samuel.gokapp.model.gokrepository.api.GokApi
import com.samuel.gokapp.model.gokrepository.service.GokApiService
import com.samuel.gokapp.viewmodel.DataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Modules {

    private val viewModelModule = module {
        viewModel { DataViewModel(get()) }
    }

    private val gokApiModule = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(GokApi::class.java)
        }

        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }

    private val repositoryModule = module {
        single { GokApiService(get()) }
    }

    val all: List<Module> = listOf(
        viewModelModule,
        gokApiModule,
        repositoryModule
    )

}