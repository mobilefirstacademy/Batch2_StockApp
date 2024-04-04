package com.example.cleanarchitectureshowcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import interactors.HomeInteractor
import routing.TempRouter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var interactor: HomeInteractor
    @Inject lateinit var router: TempRouter

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
        ViewModelFactoryProvider.INSTANCE = ViewModelFactoryProvider(
            interactor,
            router
        )
    }
}