package com.samuel.gokapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.samuel.gokapp.model.gokrepository.CashModel
import com.samuel.gokapp.model.gokrepository.DataModel
import com.samuel.gokapp.model.gokrepository.ProductsModel
import com.samuel.gokapp.model.gokrepository.SpotlightModel
import com.samuel.gokapp.model.gokrepository.service.GokApiService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DataViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<GokApiService>()
    private val onDataLoadedObserver = mockk<Observer<DataModel>>(relaxed = true)
    private val onDataErrorObserver = mockk<Observer<Boolean>>(relaxed = true)
    private val dataModel = DataModel(
        listOf(SpotlightModel("", "", "")),
        listOf(ProductsModel("", "", "")),
        CashModel("", "", "")
    )

    @Before
    fun beforeTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(Function { Schedulers.trampoline() })
    }

    @Test
    fun `when view model fetches data than it should call the repository`() {
        val viewModel = instantiateViewModel()

        val singleDataModel = Single.just(dataModel)

        every { repository.fetchData() } returns singleDataModel

        viewModel.fetchFromRemote()

        verify { repository.fetchData() }
        verify { onDataLoadedObserver.onChanged(dataModel) }

    }

    @Test
    fun `when view model fetches data and has error than throw an excpetion`() {
        val viewModel = instantiateViewModel()

        every { repository.fetchData() } returns Single.error(Throwable("Ops! Ocorreu um erro!"))

        viewModel.fetchFromRemote()

        verify { repository.fetchData() }
        verify { onDataErrorObserver.onChanged(true) }

    }

    private fun instantiateViewModel(): DataViewModel {
        val viewModel = DataViewModel(repository)
        viewModel.data.observeForever(onDataLoadedObserver)
        viewModel.dataLoadError.observeForever(onDataErrorObserver)
        return viewModel
    }
}