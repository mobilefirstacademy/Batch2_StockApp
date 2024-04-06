package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.TimeRepository
import repositories.UserAccessRepository
import routing.TempRouter
import time.TimeRepositoryImpl

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
abstract class TimeRepositoryModule {
    @Binds
    abstract fun provideTimeRepository(
        timeRepositoryImpl: TimeRepositoryImpl
    ): TimeRepository
}

@Module
@InstallIn (SingletonComponent::class)
object RouterModule {
    @Provides
    fun provideRouter(): TempRouter {
        return Router
    }
}