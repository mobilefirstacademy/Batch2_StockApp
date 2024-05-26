package com.example.presentation.di

import com.example.presentation.viewmodels.StockViewModel
import interactors.StocksInteractor

class ViewModelFactoryProvider(
    private val interactor: StocksInteractor
) {
    fun getViewModelFactory(): StockViewModel.Factory = StockViewModel.Factory(interactor)

    companion object {
        lateinit var INSTANCE: ViewModelFactoryProvider
    }
}