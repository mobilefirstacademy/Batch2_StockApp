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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R
import entities.UnitOfAccount
import entities.Stock

val defaultStocks = listOf(Stock(imageResource = R.drawable.refresh_icon))

@Preview
@Composable
fun StockPagePreview() {
    StocksPage(color = Color.White, stocks = listOf(
        Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.ic_launcher_background),
        Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.93, 131.81, true, R.drawable.ic_launcher_background),
    ))
}

@Composable
fun StocksPage(color: Color, stocks: List<Stock>) {
    val padding = dimensionResource(id = R.dimen.standard_padding)
    Surface(
        color = color,
        modifier = Modifier
            .padding(start = padding, top = padding, end = padding)
    ) {
        Column {
            SearchInput(stringResource(R.string.search_input_label))
            Tabs(tabs = listOf("Stocks", "Favourite"))
            StocksList(stocks, ::positionToColor)
        }
    }
}
fun positionToColor(index: Int): Int = if (index % 2 == 0) R.color.gray_secondary else R.color.white

@Composable
fun SearchInput(placeholder: String, value: String = "") {
    val width = dimensionResource(id = R.dimen.search_input_border_size)
    Row(
        modifier = Modifier.border(
            width = width,
            color = Color.Black,
            shape = CircleShape
        )
    ) {
        val padding = dimensionResource(id = R.dimen.icon_search_padding)
        val size = dimensionResource(id = R.dimen.icon_search_size)
        Image(
            modifier = Modifier
                .padding(start = padding, top = padding, end = padding)
                .size(size),
            painter = painterResource(id = R.drawable.search),
            contentDescription = stringResource(R.string.searching_icon_content_description),
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
fun Tabs(tabs: List<String>) {
    Surface(color = Color.White){
        val gap = dimensionResource(id = R.dimen.tabs_gap)
        val space = dimensionResource(id = R.dimen.tabs_horizontal_arrangement)
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(space),
            modifier = Modifier
                .padding(top = gap, bottom = gap)
        ) {
            Tab(tabs[0], TabStyle.CURRENT)
            Tab(tabs[1], TabStyle.ORDINARY)
        }
    }
}

@Composable
fun RowScope.Tab(name: String, tabStyle: TabStyle) {
    val weight = FontWeight.W900
    val fontSizeByID = tabStyle.fontSizeByID
    val fontColorByID = tabStyle.fontColorByID
    Text(
        text = name,
        fontWeight = weight,
        fontSize = dimensionResource(id = fontSizeByID).value.sp,
        color = colorResource(id = fontColorByID),
        modifier = Modifier.alignByBaseline()
    )
}

enum class TabStyle(val fontSizeByID: Int, val fontColorByID: Int) {
    CURRENT(R.dimen.current_tab_font_size, R.color.black),
    ORDINARY(R.dimen.ordinary_tab_font_size, R.color.gray_primary)
}


@Composable
fun StocksList(stocks: List<Stock>, posToColorRes: (Int) -> Int) {
    LazyColumn(
        Modifier.fillMaxWidth()
    ) {
        itemsIndexed(stocks) {index, stock ->
            val color = colorResource(posToColorRes(index))
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
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.standard_padding))
                )
                .fillMaxWidth()

        ) {
            Row {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = stringResource(R.string.searching_icon_description),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.stock_image_padding))
                        .size(dimensionResource(id = R.dimen.stock_image_size))
                        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.stock_image_corner_rounding)))
                )

                Column(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.standard_padding))
                ) {
                    Ticker(ticker, isFavourite)
                    CompanyName(name)
                }
            }
            Column (
                modifier = Modifier.padding(dimensionResource(id = R.dimen.standard_padding))
            ) {
                Price(currentPrice, unitOfAccount)
                CostDiff(currentPrice, purchasePrice, unitOfAccount)
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
            painter = painterResource(id = if (isFavourite) R.drawable.star_on else R.drawable.star_off),
            contentDescription = "not favorite",
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.ticker_star_image_padding))
                .size(dimensionResource(id = R.dimen.ticker_star_image_size))
        )
    }
}

@Composable
fun CompanyName(name: String) {
    Text(
        text = name,
        fontWeight = FontWeight.W600,
        fontSize = dimensionResource(id = R.dimen.company_name_text_size).value.sp
    )
}

@Composable
fun Price(price: Double, unitOfAccount: UnitOfAccount) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = priceFormat(price, unitOfAccount, false),
            color = colorResource(id = R.color.black),
        ) }
}


@Composable
fun CostDiff(currentPrice: Double, purchasePrice: Double, unit: UnitOfAccount) {
    val (text, fontColorById) = costDiffModel(currentPrice,purchasePrice,unit)
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = text, color = colorResource(id = fontColorById))
    }
}
