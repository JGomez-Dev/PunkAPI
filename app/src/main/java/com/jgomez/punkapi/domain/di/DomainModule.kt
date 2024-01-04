package com.jgomez.punkapi.domain.di

import com.jgomez.punkapi.domain.repository.BeerRepository
import com.jgomez.punkapi.domain.usecase.beer.GetBeerByIdUseCase
import com.jgomez.punkapi.domain.usecase.beer.GetBeersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetBeersUseCase(tripsRepository: BeerRepository): GetBeersUseCase {
        return GetBeersUseCase(tripsRepository)
    }

    @Provides
    fun provideGetBeerByIdUseCase(tripsRepository: BeerRepository): GetBeerByIdUseCase {
        return GetBeerByIdUseCase(tripsRepository)
    }

}