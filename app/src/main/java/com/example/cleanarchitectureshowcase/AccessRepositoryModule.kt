package com.example.cleanarchitectureshowcase

import access.AccessRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repositories.UserAccessRepository

@Module
@InstallIn (SingletonComponent::class)
object AccessRepositoryModule {
    @Provides
    fun provideAccessRepository(): UserAccessRepository {
        return AccessRepositoryImpl()
    }
}