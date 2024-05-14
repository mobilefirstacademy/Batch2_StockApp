package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.letsgo.LetsGoFragment
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.StocksFragment
import interactors.HomeInteractor
import interactors.StocksInteractor
import repositories.TimeRepository
import repositories.UserAccessRepository
import routing.TempRouter
import stocks.StocksRepositoryImpl
import time.TimeRepositoryImpl
import time.TimeService

class MainActivity : AppCompatActivity() {
    private val router: TempRouter by lazy(::createRouter)
    private val stocksInteractor = StocksInteractor(
        StocksRepositoryImpl()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEntry()
        initDi()
    }

    private fun initEntry() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, StocksFragment.newInstance(stocksInteractor.getOwnStocks()))
            .commit()
    }
    private fun initDi() {
        // TODO: Почитать про di выбрать какой полегче (из hilt или koin)
//        val timeService = TimeService()
//        val accessRepository: UserAccessRepository = AccessRepositoryImpl()
//        val timeRepository: TimeRepository = TimeRepositoryImpl(timeService)
        val stocksRepository = StocksRepositoryImpl()
        val interactor = StocksInteractor(stocksRepository)
        val router = createRouter()
        ViewModelFactoryProvider.INSTANCE = ViewModelFactoryProvider(
            interactor,
            router
        )
    }

    private fun createRouter() = object : TempRouter {
        override fun goTo_letsGo(name: String) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, LetsGoFragment.newInstance(name))
                .addToBackStack(null)
                .commit()
        }
    }
}