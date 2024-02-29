package com.example.presentation.viewmodels.time

import androidx.lifecycle.LiveData
import exceptions.HomeException
import exceptions.Reason
import interactors.HomeInteractor
import java.lang.Exception
import java.util.Date
class TimeLivedata(
    private val interactor: HomeInteractor,
    initialTime: Time
) : LiveData<Result<Time>>() {
    init {
        value = Result.success(initialTime)
    }
    fun refresh() {
        interactor.onTimeRefreshRequest()
            .onSuccess { time ->
                val timeStr = Date(time.timeInt).toString()
                value = Result.success(Time(timeStr))
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