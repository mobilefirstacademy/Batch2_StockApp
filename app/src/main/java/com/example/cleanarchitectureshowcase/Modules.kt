package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import androidx.fragment.app.FragmentManager
import com.example.letsgo.LetsGoFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import repositories.TimeRepository
import repositories.UserAccessRepository
import routing.TempRouter
import time.TimeRepositoryImpl
import time.TimeService

@Module
@InstallIn (SingletonComponent::class)
abstract class AccessRepositoryModule {
    @Binds
    abstract fun provideAccessRepository(
        accessRepositoryImpl: AccessRepositoryImpl
    ): UserAccessRepository
}

@Module
@InstallIn (SingletonComponent::class)
object TimeRepositoryModule {
    @Provides
    fun provideTimeRepository(timeService: TimeService): TimeRepository {
        return TimeRepositoryImpl(timeService)
    }
}

@Module
@InstallIn (ActivityComponent::class)
object RouterModule {
    @Provides
    fun provideRouter(
        fragmentManager: FragmentManager
    ): TempRouter {
        return object : TempRouter {
            override fun goTo_letsGo(name: String) {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment, LetsGoFragment.newInstance(name))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}