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
import androidx.lifecycle.ViewModelProvider
import com.example.home.R
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.composable.StocksPage
import com.example.presentation.viewmodels.StockViewModel
import entities.Stock

val loadedStocksData = listOf(Stock(imageResource = R.drawable.refresh_icon))
class StocksFragment : Fragment() {
    private val viewModel: StockViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            ViewModelFactoryProvider.INSTANCE.getViewModelFactory()
        )[StockViewModel::class.java]
    }

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
                            loadedStocksData
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StocksFragment()
    }
}

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Montserrat")
val Montserrat = FontFamily( Font(googleFont = fontName, fontProvider = provider) )