package entities

data class Stock(
    val ticker: String = "LOAD",
    val name: String = "loading...",
    val unitOfAccount: UnitOfAccount = UnitOfAccount.USD,
    val purchasePrice: Double = 0.0,
    val currentPrice: Double = 0.0,
    val isFavourite: Boolean = false,
    val imageResource: Int,
)