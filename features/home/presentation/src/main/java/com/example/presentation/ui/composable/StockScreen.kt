package com.example.presentation.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.home.R
import com.example.presentation.ui.Montserrat
import com.example.presentation.ui.Stock
import com.example.presentation.ui.UnitOfAccount
import com.example.presentation.ui.basicStocks

@Preview
@Composable
fun PreviewStockPage() {
    MaterialTheme(
        typography = Typography(
            bodyLarge = TextStyle(fontFamily = Montserrat)
        )
    ) {
        StocksPage(Color.White)
    }
}

@Composable
fun StocksPage(color: Color) {
    val padding = 15.dp
    Surface(
        color = color,
        modifier = Modifier
            .padding(start = padding, top = padding, end = padding)
    ) {
        Column {
            InputSearch("Find company or ticker") // поисковая строка
            Tabs(tabs = listOf("Stocks", "Favourite"), 0) // вкладки
            StocksList(stocks = basicStocks)
        }
    }
}

@Composable
fun InputSearch(placeholder: String, value: String = "") {
    Row(
        modifier = Modifier.border(
            width = 1.dp,
            color = Color.Black,
            shape = CircleShape
        )
    ) {
        Image(
            modifier = Modifier
                .padding(15.dp, 15.dp, 0.dp, 15.dp)
                .size(25.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "searching icon",
        )
        TextField(
            value = value,
            onValueChange = {},
            placeholder = { Text(placeholder) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        )
    }
}

@Composable
fun Tabs(tabs: List<String>, currentIndex: Int) {
    Surface(color = Color.White){
        val gap = 15.dp
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .padding(top = gap, bottom = gap)
        ) {
            Tab(tabs[0], true)
            Tab(tabs[1], false)
        }
    }
}

@Composable
fun RowScope.Tab(name: String, isCurrent: Boolean) {
    val weight = FontWeight.W900
    val fontSize = if (isCurrent) 30F else 20F
    val fontColor = if (isCurrent) Color.Black else Color(0xFFBABABA)
    Text(
        text = name,
        fontWeight = weight,
        fontSize = TextUnit(fontSize, TextUnitType.Sp),
        color = fontColor,
        modifier = Modifier.alignByBaseline()
    )
}


@Composable
fun StocksList(stocks: List<Stock>) {
    LazyColumn(
        Modifier.fillMaxWidth()
    ) {
        itemsIndexed(stocks) {index, stock ->
            val color = if (index % 2 == 0) Color(0xFFF0F4F7) else Color.White
            StockItem(stock, color)
        }
    }
}

@Composable
fun StockItem(stock: Stock, backgroundColor: Color) {
    stock.run {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .fillMaxWidth()

        ) {
            Row {
                Image(

                    painter = painterResource(id = imageResource),
                    contentDescription = "searching icon",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Ticker(ticker, isFavourite)
                    CompanyName(name)
                }
            }
            Column (
                modifier = Modifier.padding(10.dp)
            ) {
                val diff = String.format("%.2f", currentPrice / purchasePrice)
                val absoluteDiff = currentPrice - purchasePrice
                Price(currentPrice, unitOfAccount)
                Diff(diff, absoluteDiff, unitOfAccount)
            }
        }
    }
}

@Composable
fun Ticker(ticker: String, isFavourite: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(text = ticker, fontWeight = FontWeight.W900)
        Image(
            painter = painterResource(id = if (isFavourite) R.drawable.star_off else R.drawable.star_on),
            contentDescription = "not favorite",
            modifier = Modifier
                .padding(5.dp)
                .size(16.dp)
        )
    }
}

@Composable
fun CompanyName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.W600,
        fontSize = TextUnit(10F, TextUnitType.Sp)
    )
}

@Composable
fun Price(price: Double, unitOfAccount: UnitOfAccount) {
    val sign = when(unitOfAccount) {
        UnitOfAccount.RUB -> "₽"
        UnitOfAccount.USD -> "$"
        UnitOfAccount.EUR -> "€"
    }
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth()
    ){ Text(text = priceFormat(unitOfAccount, price)) }
}

fun priceFormat(unit: UnitOfAccount, count: Double): String {
    val preUnit = when(unit) {
        UnitOfAccount.USD -> "$"
        else -> ""
    }
    val postUnit = when(unit) {
        UnitOfAccount.RUB -> " ₽"
        UnitOfAccount.EUR -> "€"
        UnitOfAccount.USD -> ""
        else -> ""
    }
    return preUnit + String.format("%.2f", count) + postUnit
}
@Composable
fun Diff(diff: String, absoluteDiff: Double, unit: UnitOfAccount) {
    val sign = if (absoluteDiff > 0) "+" else "-"
    val fontColor = if (absoluteDiff > 0) Color(0xFF24B25D) else Color.Black
    val absDiffFormatted: String = "$sign${priceFormat(unit, absoluteDiff)}"
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = "$absDiffFormatted ($diff%)", color = fontColor)
    }
}