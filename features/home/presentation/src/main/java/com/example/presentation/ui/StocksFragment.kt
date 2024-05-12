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
import com.example.presentation.ui.composable.StocksPage
import entities.Stock
import entities.UnitOfAccount

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
                    val parcelableStocks: Array<Parcelable> =
                        stocks.map { stock -> ParcelableStock(stock) }.toTypedArray()
                    putParcelableArrayList("STOCKS", arrayListOf(*parcelableStocks))
                }
            }

    }

    class ParcelableStock(private val stock: Stock): Parcelable {
        override fun describeContents(): Int = 0

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(stock.ticker)
            dest.writeString(stock.name)
            dest.writeString(stock.unitOfAccount.toString())
            dest.writeDouble(stock.purchasePrice)
            dest.writeDouble(stock.currentPrice)
            dest.writeBoolean(stock.isFavourite)
            dest.writeInt(stock.imageResource)
        }

        companion object CREATOR : Parcelable.Creator<Stock> {
            @RequiresApi(Build.VERSION_CODES.Q)
            override fun createFromParcel(parcel: Parcel): Stock {
                return Stock(
                    ticker = parcel.readString()!!,
                    name = parcel.readString()!!,
                    unitOfAccount = UnitOfAccount.valueOf(parcel.readString()!!),
                    purchasePrice = parcel.readDouble(),
                    currentPrice = parcel.readDouble(),
                    isFavourite = parcel.readBoolean(),
                    imageResource = parcel.readInt(),
                )
            }

            override fun newArray(size: Int): Array<Stock> {
                TODO("Not yet implemented")
            }
        }
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
