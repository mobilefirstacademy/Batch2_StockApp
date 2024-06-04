package com.example.data.stock

object StockProvider {
    private const val BASE_URL = "https://financialmodelingprep.com/api/v3/"
    val stockApi: StockSearchingApi
        get() = RetrofitClient.getClient(BASE_URL).create(StockSearchingApi::class.java)
}