package com.example.cleanarchitectureshowcase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.di.ViewModelFactoryProvider
import com.example.presentation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModelFactoryProvider by viewModels()

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

    }
}