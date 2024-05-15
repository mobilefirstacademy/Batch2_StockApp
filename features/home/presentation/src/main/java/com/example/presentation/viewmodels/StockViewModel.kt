package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.home.R
import com.example.presentation.viewmodels.stocks.StocksLivedata
import entities.Stock
import interactors.StocksInteractor

class StockViewModel(
    interactor: StocksInteractor,
): ViewModel() {

    private val _stocksLivedata = StocksLivedata(interactor, listOf(Stock(imageResource = R.drawable.refresh_icon)))
    val stocksLivedata: LiveData<Result<List<Stock>>> = _stocksLivedata

    fun refreshStocks() {
        _stocksLivedata.refresh()
    }

    class Factory(
        private val interactor: StocksInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
                return StockViewModel(interactor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
