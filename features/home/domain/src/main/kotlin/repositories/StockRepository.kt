package repositories

import entities.Stock

interface StockRepository {
    fun getOwnStocks(): List<Stock>
}