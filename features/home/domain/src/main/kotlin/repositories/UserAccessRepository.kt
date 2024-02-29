package repositories

interface UserAccessRepository {
    fun enoughMoneyOnBalance(): Boolean
    fun countrySupportsTimeRefresh(): Boolean
}