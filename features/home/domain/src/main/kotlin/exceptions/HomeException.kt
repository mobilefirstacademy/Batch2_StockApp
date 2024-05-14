package exceptions

class HomeException(val reason: Reason) : Exception(reason.message)

open class Reason(val message: String? = null) {
    object TOO_FEW_MONEY_ON_BALANCE: Reason()
    object COUNTRY_RESTRICTIONS: Reason()
    object STOCKS_ARE_NOT_AVAILABLE: Reason()
}