package entities

data class Stock(
    val ticker: String,
    val name: String,
    val unitOfAccount: UnitOfAccount,
    val purchasePrice: Double,
    val currentPrice: Double,
    val isFavourite: Boolean,
    val imageResource: Int,
)