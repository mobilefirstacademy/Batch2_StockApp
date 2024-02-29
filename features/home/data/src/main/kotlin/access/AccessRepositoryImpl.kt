package access

import repositories.UserAccessRepository

class AccessRepositoryImpl : UserAccessRepository {
    override fun enoughMoneyOnBalance(): Boolean {
        // Сходили на сервер, узнали...
        return (requestsCount < 3).also { requestsCount++ }
    }

    override fun countrySupportsTimeRefresh(): Boolean {
        // Сходили на сервер, узнали...
        return true
    }
}

private var requestsCount = 0