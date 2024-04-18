package com.example.stockgetter

object StockProvider {
    private const val BASE_URL = "https://financialmodelingprep.com/api/v3/"
    val stockApi: StockApi
        get() = RetrofitClient.getClient(BASE_URL).create(StockApi::class.java)
}