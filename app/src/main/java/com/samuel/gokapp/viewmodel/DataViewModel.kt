package com.samuel.gokapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.samuel.gokapp.model.gokrepository.DataModel
import com.samuel.gokapp.model.gokrepository.service.GokApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DataViewModel(
    private val gokApiService: GokApiService
) : BaseViewModel() {

    val data = MutableLiveData<DataModel>()
    val dataLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            gokApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DataModel>() {
                    override fun onSuccess(incomingData: DataModel) {
                        data.value = incomingData
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        dataLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

}