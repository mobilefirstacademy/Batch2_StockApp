package com.example.cleanarchitectureshowcase.data

import access.AccessRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.TimeRepository
import repositories.UserAccessRepository
import time.TimeRepositoryImpl

@Module
@InstallIn (SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAccessRepository(
        accessRepositoryImpl: AccessRepositoryImpl
    ): UserAccessRepository

    @Binds
    abstract fun provideTimeRepository(
        timeRepositoryImpl: TimeRepositoryImpl
    ): TimeRepository
}