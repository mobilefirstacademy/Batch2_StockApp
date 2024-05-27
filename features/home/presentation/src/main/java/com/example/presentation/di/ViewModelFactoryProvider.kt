package com.example.presentation.di

import com.example.presentation.viewmodels.HomeViewModel
import interactors.HomeInteractor

class ViewModelFactoryProvider(
    private val interactor: HomeInteractor,
) {
    fun getViewModelFactory(): HomeViewModel.Factory = HomeViewModel.Factory(interactor)

    companion object {
        lateinit var INSTANCE: ViewModelFactoryProvider
    }
}