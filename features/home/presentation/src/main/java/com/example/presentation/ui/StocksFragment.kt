package com.example.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R
import kotlinx.coroutines.channels.ticker

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
    val price: Double,
    val diff: Double,
    val isFavourite: Boolean,
)
val basicStocks = listOf(
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB, 4764.6, 1.15, false),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.93, 1.15, true),
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
            Tabs(tabs = listOf("Stocks", "Favorite")) // вкладки
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
            painter = painterResource(id = R.drawable.refresh_icon), // TODO: change picture
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
fun Tabs(tabs: List<String>) {
    Surface{
        Row {
            tabs.forEach {
                Tab(it)
            }
        }
    }
}

@Composable
fun Tab(name: String) {
    Text(text = name)
}

@Composable
fun StocksList(stocks: List<Stock>) {
    LazyColumn(
        Modifier.fillMaxWidth()
    ) {
        items(stocks) {stock ->
            StockItem(stock)
        }
    }
}

@Composable
fun StockItem(stock: Stock) {
    stock.run {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(
                    color = Color(0xFFF0F4F7),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()

        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.refresh_icon), // TODO: change picture
                    contentDescription = "searching icon",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(45.dp)
                )

                Column {
                    Ticker(ticker, isFavourite)
                    CompanyName(name)
                }
            }
            Column {
                Price(price, unitOfAccount)
                Diff(diff)
            }
        }
    }
}

@Composable
fun Ticker(ticker: String, isFavourite: Boolean) {
    Row{
        Text(text = ticker)
        Text(
            text = "★",
            color = if (isFavourite) Color(0xFFFFCA1C) else Color(0xFFBABABA),
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
    Text(text = "$price $sign")
}

@Composable
fun Diff(diff: Double) {
    Text(text = "($diff)%", )
}