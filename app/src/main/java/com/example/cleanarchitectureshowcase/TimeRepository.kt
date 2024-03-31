package com.example.cleanarchitectureshowcase


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import repositories.TimeRepository
import time.TimeRepositoryImpl
import time.TimeService

@Module
@InstallIn (ActivityComponent::class)
object TimeRepositoryModule {
    @Provides
    fun provideTimeRepository(): TimeRepository {
        return TimeRepositoryImpl(TimeService())
    }
}