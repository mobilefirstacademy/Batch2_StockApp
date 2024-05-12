package interactors

import repositories.StockRepository

class StocksInteractor(
    private val stockRepository: StockRepository
) {
    fun getOwnStocks() = stockRepository.getOwnStocks()
}