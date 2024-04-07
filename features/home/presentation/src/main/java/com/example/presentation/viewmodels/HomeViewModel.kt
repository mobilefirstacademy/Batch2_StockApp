package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.viewmodels.time.Time
import com.example.presentation.viewmodels.time.TimeLivedata
import interactors.HomeInteractor

class HomeViewModel(
    interactor: HomeInteractor,
): ViewModel() {

    private val _timeLivedata = TimeLivedata(interactor, Time("Push to refresh ->"))
    val timeLivedata: LiveData<Result<Time>> = _timeLivedata

    fun refreshTime() {
        _timeLivedata.refresh()
    }

    class Factory(
        private val interactor: HomeInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(interactor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
