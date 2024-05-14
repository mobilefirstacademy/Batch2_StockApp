package com.example.presentation.di

import com.example.presentation.viewmodels.HomeViewModel
import com.example.presentation.viewmodels.StockViewModel
import interactors.HomeInteractor
import interactors.StocksInteractor
import routing.TempRouter

class ViewModelFactoryProvider(
    private val interactor: StocksInteractor,
    private val router: TempRouter,
) {
    fun getViewModelFactory(): StockViewModel.Factory = StockViewModel.Factory(router, interactor)

    companion object {
        lateinit var INSTANCE: ViewModelFactoryProvider
    }
}