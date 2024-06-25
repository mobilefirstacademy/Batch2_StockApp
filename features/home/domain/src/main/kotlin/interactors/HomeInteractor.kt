package interactors

import entities.StockModel
import entities.Time
import exceptions.HomeException
import exceptions.Reason
import repositories.StockRepository
import repositories.TimeRepository
import repositories.UserAccessRepository

class HomeInteractor(
    private val stockRepository: StockRepository,
    private val accessRepository: UserAccessRepository,
    private val timeRepository: TimeRepository,
) {
    fun onTimeRefreshRequest(): Result<Time> = runCatching {
        if (!accessRepository.enoughMoneyOnBalance()) throw HomeException(Reason.TOO_FEW_MONEY_ON_BALANCE)
        if (!accessRepository.countrySupportsTimeRefresh()) throw HomeException(Reason.COUNTRY_RESTRICTIONS)
        Time(timeRepository.getUnixTime())
    }
    fun onStockSearchRequest(): Result<List<StockModel>> = runCatching {
        // TODO: exception
        var result: List<StockModel>? = null
        stockRepository.filterStocksByQuery {
            result = it
        }
        result!!
    }
}