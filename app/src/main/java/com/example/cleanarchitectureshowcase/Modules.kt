package com.example.cleanarchitectureshowcase

import androidx.fragment.app.FragmentManager
import com.example.letsgo.LetsGoFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import routing.TempRouter

@Module
@InstallIn (SingletonComponent::class)
object RouterModule {
    @Provides
    fun provideRouter(): TempRouter {
        return object: TempRouter {
            override fun goTo_letsGo(name: String, manager: FragmentManager) {
                manager.beginTransaction()
                    .replace(R.id.fragment, LetsGoFragment.newInstance(name))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}