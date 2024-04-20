package com.example.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.home.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StocksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StocksFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        // Compose
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                StocksPage()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StocksFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StocksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

enum class UnitOfAccount {
    RUB, USD, EUR
}
class Stock(
    val ticker: String,
    val name: String,
    val unitOfAccount: UnitOfAccount,
    val purchasePrice: Double,
    val currentPrice: Double,
    val isFavourite: Boolean,
    val imageResource: Int,
)
val basicStocks = listOf(
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
)

@Preview
@Composable
fun PreviewStockPage() {
    MaterialTheme {
        StocksPage()
    }
}

@Composable
fun StocksPage() {
    Surface(
        Modifier.padding(15.dp)
    ) {
        Column {
            InputSearch("Find company or ticker") // поисковая строка
            Tabs(tabs = listOf("Stocks", "Favorite"), 0) // вкладки
            StocksList(stocks = basicStocks)// TODO: список тикеров
            // TODO:
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
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        )
    }
}

@Composable
fun Tabs(tabs: List<String>, currentIndex: Int) {
    Surface{
        Row {
            Tab(tabs[0], true)
            Tab(tabs[1], false)
        }
    }
}

@Composable
fun Tab(name: String, isCurrent: Boolean) {
    val weight = if (isCurrent) FontWeight.Bold else null
    Text(text = name, fontWeight = weight)
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
        Text(text = ticker)
        Text(
            text = "★",
            color = if (isFavourite) Color(0xFFFFCA1C) else Color(0xFFBABABA),
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Top)
        )
    }
}

@Composable
fun CompanyName(name: String) {
    Text(text = name)
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