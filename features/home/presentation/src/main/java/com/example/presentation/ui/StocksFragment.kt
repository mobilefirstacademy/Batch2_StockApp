package com.example.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.fragment.app.Fragment
import com.example.home.R
import com.example.presentation.ui.composable.StocksPage

class StocksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val globalBackgroundColor = Color.White
                MaterialTheme(
                    typography = Typography(
                        bodyLarge = TextStyle(fontFamily = Montserrat)
                    ),
                ) {
                    Surface(color = globalBackgroundColor) { StocksPage(globalBackgroundColor) }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StocksFragment()
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
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
    Stock("YNDX", "Yandex, LCC", UnitOfAccount.RUB,4709.6, 4764.6, false, R.drawable.yandex),
    Stock("AAPL", "Apple Inc.", UnitOfAccount.USD, 131.81, 131.93, true, R.drawable.apple),
)

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Montserrat")
val Montserrat = FontFamily( Font(googleFont = fontName, fontProvider = provider) )

