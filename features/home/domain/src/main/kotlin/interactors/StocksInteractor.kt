package interactors

import entities.Stock
import exceptions.HomeException
import exceptions.Reason
import repositories.StockRepository

class StocksInteractor(
    private val stockRepository: StockRepository
) {
    fun getOwnStocks() = stockRepository.getOwnStocks()
    fun onTimeRefreshRequest(): Result<List<Stock>> = runCatching {
        if (!stockRepository.stocksAreAvailable()) throw HomeException(Reason.STOCKS_ARE_NOT_AVAILABLE)
        getOwnStocks()
    }
}