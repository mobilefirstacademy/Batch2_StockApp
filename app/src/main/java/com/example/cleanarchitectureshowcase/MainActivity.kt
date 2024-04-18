package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.letsgo.LetsGoFragment
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.HomeFragment
import com.example.stockgetter.TempStockListFragment
import interactors.HomeInteractor
import repositories.TimeRepository
import repositories.UserAccessRepository
import routing.TempRouter
import time.TimeRepositoryImpl
import time.TimeService

class MainActivity : AppCompatActivity() {
    private val router: TempRouter by lazy(::createRouter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEntry()
        initDi()
    }

    private fun initEntry() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, HomeFragment.newInstance(
                picId = R.drawable.greeting_pic,
                "Приветствуем! \nПоздравляем с новым этапом обучения! \nРабота уже не за горами )"
            ))
            .commit()
    }
    private fun initDi() {
        // TODO: Почитать про di выбрать какой полегче (из hilt или koin)
        val timeService = TimeService()
        val accessRepository: UserAccessRepository = AccessRepositoryImpl()
        val timeRepository: TimeRepository = TimeRepositoryImpl(timeService)
        val interactor = HomeInteractor(
            accessRepository,
            timeRepository
        )
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

        override fun goTo_stockList() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, TempStockListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}