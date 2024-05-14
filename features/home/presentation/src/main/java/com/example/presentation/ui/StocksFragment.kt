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
import entities.Stock

class StocksFragment : Fragment() {
    private var stocks: List<Stock> = listOf(Stock(imageResource = R.drawable.refresh_icon)) // TODO: change default list

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val globalBackgroundColor = Color.White
                MaterialTheme(
                    typography = Typography(
                        bodyLarge = TextStyle(fontFamily = Montserrat)
                    ),
                ) {
                    Surface(color = globalBackgroundColor) {
                        StocksPage(
                            globalBackgroundColor,
                            stocks
                        )
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(stocks: List<Stock>) = StocksFragment()
    }
}

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Montserrat")
val Montserrat = FontFamily( Font(googleFont = fontName, fontProvider = provider) )