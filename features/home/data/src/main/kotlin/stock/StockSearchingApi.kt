package com.example.data.stock

import entities.StockModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockSearchingApi {
    @GET("stock-screener")
    fun getStockList(@Query("apikey") apiKey: String): Call<MutableList<StockModel>>
}