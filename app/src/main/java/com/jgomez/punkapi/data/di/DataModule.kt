package com.jgomez.punkapi.data.di

import com.jgomez.punkapi.core.constans.Constants
import com.jgomez.punkapi.data.datasource.remote.BeerApiService
import com.jgomez.punkapi.data.repository.BeerRepositoryImpl
import com.jgomez.punkapi.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBeerApiService(retrofit: Retrofit): BeerApiService {
        return retrofit.create(BeerApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerRepository(beerApiService: BeerApiService): BeerRepository {
        return BeerRepositoryImpl(beerApiService)
    }

}
