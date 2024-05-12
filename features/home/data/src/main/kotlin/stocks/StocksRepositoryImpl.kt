package stocks

import com.example.data.R
import entities.Stock
import entities.UnitOfAccount
import repositories.StockRepository

class StocksRepositoryImpl: StockRepository {
    override fun getOwnStocks(): List<Stock> = listOf(
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    )
}