package com.example.presentation.di

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import interactors.HomeInteractor
import routing.TempRouter
import javax.inject.Inject

@HiltViewModel
class ViewModelFactoryProvider @Inject constructor(
    private val interactor: HomeInteractor,
    private val router: TempRouter,
) : ViewModel()