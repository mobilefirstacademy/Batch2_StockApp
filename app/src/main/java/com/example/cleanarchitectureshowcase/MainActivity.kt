package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.di.ViewModelFactoryProvider
import interactors.HomeInteractor
import repositories.TimeRepository
import repositories.UserAccessRepository
import time.TimeRepositoryImpl
import time.TimeService

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEntry()
        initDi()
    }

    private fun initEntry() {
        val bundle = Bundle()
        bundle.putInt(PIC_ID, R.drawable.greeting_pic)
        bundle.putString(GREETING, "Приветствуем! \nПоздравляем с новым этапом обучения! \nРабота уже не за горами )")

        navHostFragment  = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph, bundle)
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
        ViewModelFactoryProvider.INSTANCE = ViewModelFactoryProvider(
            interactor
        )
    }
}

private const val PIC_ID = "PIC_ID"
private const val GREETING = "GREETING"
private const val NAME = "NAME"

// Что-то написано