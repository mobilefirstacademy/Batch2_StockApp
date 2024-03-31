package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.letsgo.LetsGoFragment
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import interactors.HomeInteractor
import routing.TempRouter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var interactor: HomeInteractor
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