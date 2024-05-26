package com.example.presentation.ui.composable

import com.example.home.R
import entities.UnitOfAccount
import kotlin.math.abs
import kotlin.math.floor

data class ColoredText(val text: String, val fontColorByID: Int)

fun priceFormat(price: Double, unit: UnitOfAccount, hasSign: Boolean): String {
    var sign = ""
    if (hasSign) {
        sign = if (price >= 0.0) "+" else "-"
    }
    var preUnit = ""
    var postUnit = ""
    val value = abs(floor(price * 100.0) / 100.0)
    when (unit) {
        UnitOfAccount.RUB -> postUnit = " ₽"
        UnitOfAccount.USD -> preUnit = "$"
        UnitOfAccount.EUR -> preUnit = "€"
    }

    return "$sign$preUnit$value$postUnit"
}
fun costDiffModel(purchasePrice: Double, currentPrice: Double, unit: UnitOfAccount): ColoredText {
    val absoluteDifference = currentPrice - purchasePrice
    val min: Double = if (absoluteDifference >= 0) purchasePrice else currentPrice
    val max: Double = if (absoluteDifference < 0) currentPrice else purchasePrice
    val relativeDifference: Double = if (min != 0.0) max/min else 0.0

    val percentageAsString: String = String.format("%.2f", relativeDifference)
    val fontColor = if (absoluteDifference > 0) R.color.positive_margin else R.color.negative_margin
    return ColoredText("${priceFormat(absoluteDifference, unit, true)}($percentageAsString%)", fontColor)
}