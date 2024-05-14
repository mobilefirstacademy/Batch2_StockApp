package com.example.presentation.viewmodels.time

import androidx.lifecycle.LiveData
import entities.Stock
import exceptions.HomeException
import exceptions.Reason
import interactors.HomeInteractor
import interactors.StocksInteractor
import java.lang.Exception
import java.util.Date
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
                    Reason.COUNTRY_RESTRICTIONS -> Exception("It is not allowed in your country :(")
                    Reason.TOO_FEW_MONEY_ON_BALANCE -> Exception("Top up your balance to keep using all services :(")
                    else -> unknownException()
                })
            }
    }

    companion object {
        private fun unknownException() = Exception("Unknown error occurred during time refreshing :(")
    }
}