package com.example.presentation.viewmodels.stocks

import androidx.lifecycle.LiveData
import entities.Stock
import exceptions.HomeException
import exceptions.Reason
import interactors.StocksInteractor
import java.lang.Exception

class StocksLivedata(
    private val interactor: StocksInteractor,
    stocks: List<Stock>
) : LiveData<Result<List<Stock>>>() {
    init {
        value = Result.success(stocks)
    }
    fun refresh() {
        interactor.onTimeRefreshRequest()
            .onSuccess { stocks: List<Stock> ->
                value = Result.success(stocks)
            }
            .onFailure { ex ->
                if (ex !is HomeException) {
                    value = Result.failure(unknownException())
                    return@onFailure
                }
                value = Result.failure(when(ex.reason) {
                    Reason.STOCKS_ARE_NOT_AVAILABLE -> Exception("Stocks are not available inside repository.")
                    else -> unknownException()
                })
            }
    }

    companion object {
        private fun unknownException() = Exception("Unknown error occurred during list of stocks refreshing :(")
    }
}