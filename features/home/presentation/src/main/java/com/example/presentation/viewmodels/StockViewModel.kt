package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.R
import com.example.presentation.viewmodels.time.StocksLivedata
import entities.Stock
import interactors.HomeInteractor
import interactors.StocksInteractor
import routing.TempRouter

class StockViewModel(
    private val router: TempRouter,
    interactor: StocksInteractor,
): ViewModel() {

    private val _stocksLivedata = StocksLivedata(interactor, listOf(Stock(imageResource = R.drawable.refresh_icon)))
    val timeLivedata: LiveData<Result<List<Stock>>> = _stocksLivedata

    fun refreshTime() {
        _stocksLivedata.refresh()
    }

    fun letsGo(name: String?) {
        // If ... and ...
        router.goTo_letsGo(name ?: "Anonymous")
    }

    class Factory(
        private val router: TempRouter,
        private val interactor: StocksInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
                return StockViewModel(router, interactor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
