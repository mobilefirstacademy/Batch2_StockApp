package com.example.stockgetter

data class StockModel (
    val symbol: String?,
    val name: String?,
    val price: Double?,
    val exchange: String?,
    val exchangeShortName: String?,
    val type: String?
)