package repositories

import entities.StockModel

interface StockRepository {
    fun filterStocksByQuery(callback: (List<StockModel>?) -> Unit)
}