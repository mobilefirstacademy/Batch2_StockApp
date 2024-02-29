package com.example.presentation.di

import com.example.presentation.viewmodels.HomeViewModel
import interactors.HomeInteractor
import routing.TempRouter

class ViewModelFactoryProvider(
    private val interactor: HomeInteractor,
    private val router: TempRouter,
) {
    fun getViewModelFactory(): HomeViewModel.Factory = HomeViewModel.Factory(router, interactor)

    companion object {
        lateinit var INSTANCE: ViewModelFactoryProvider
    }
}