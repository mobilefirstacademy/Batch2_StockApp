package com.example.presentation.ui

import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.BundleCompat.getParcelable
import androidx.core.os.BundleCompat.getParcelableArrayList
import androidx.fragment.app.Fragment
import com.example.home.R
import com.example.presentation.ui.StocksFragment.ParcelableStock.write
import com.example.presentation.ui.composable.StocksPage
import entities.Stock
import entities.UnitOfAccount
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

class StocksFragment : Fragment() {
    private var stocks: List<Parcelable>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        stocks = getParcelableArrayList(savedInstanceState!!, "STOCKS", ParcelableStock::class.java)
        return ComposeView(requireContext()).apply {
            setContent {
                val globalBackgroundColor = Color.White
                MaterialTheme(
                    typography = Typography(
                        bodyLarge = TextStyle(fontFamily = Montserrat)
                    ),
                ) {
                    Surface(color = globalBackgroundColor) { StocksPage(globalBackgroundColor, stocks) }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(stocks: List<Stock>) =
            StocksFragment().apply {
                arguments = Bundle().apply {
                    val parcel = Parcel.obtain()
                    val parcelableStocks: Array<Parcelable> = arrayOf()
                    putParcelableArrayList("STOCKS", arrayListOf(*parcelableStocks))
                }
            }

    }

    object ParcelableStock : Parceler<Stock> {
        override fun create(parcel: Parcel): Stock = Stock(
            ticker = parcel.readString()!!,
            name = parcel.readString()!!,
            unitOfAccount = UnitOfAccount.valueOf(parcel.readString()!!),
            purchasePrice = parcel.readDouble(),
            currentPrice = parcel.readDouble(),
            isFavourite = parcel.readBoolean(),
            imageResource = parcel.readInt(),
        )

        override fun Stock.write(parcel: Parcel, flags: Int) {
            parcel.writeString(this.ticker)
            parcel.writeString(this.name)
            parcel.writeString(this.unitOfAccount.toString())
            parcel.writeDouble(this.purchasePrice)
            parcel.writeDouble(this.currentPrice)
            parcel.writeBoolean(this.isFavourite)
            parcel.writeInt(this.imageResource)
        }

        @Parcelize
        data class Stocks(val stocks: List<ParcelableStock>): Parcelable

    }


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Montserrat")
val Montserrat = FontFamily( Font(googleFont = fontName, fontProvider = provider) )

@Preview
@Composable
fun PreviewStockPage() {
    MaterialTheme(
        typography = Typography(
            bodyLarge = TextStyle(fontFamily = Montserrat)
        )
    ) {
        StocksPage(Color.White, stocks = )
    }
}
