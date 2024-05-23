package com.example.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("stock/list")
    fun getStockList(@Query("apikey") apiKey: String): Call<MutableList<StockModel>>
}