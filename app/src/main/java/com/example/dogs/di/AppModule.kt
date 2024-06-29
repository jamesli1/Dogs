package com.example.dogs.di

import com.example.dogs.data.api.ApiService
import com.example.dogs.data.repository.DogRepository
import com.example.dogs.data.repository.DogRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Singleton
    @Provides
    fun provideDogRepository(api: ApiService): DogRepository {
        return DogRepositoryImpl(api)
    }
}
