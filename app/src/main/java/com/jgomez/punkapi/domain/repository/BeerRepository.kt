package com.jgomez.punkapi.domain.repository

import com.jgomez.punkapi.core.utils.Either
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.model.GenericError

interface BeerRepository {
    suspend fun getBeers(): Either<List<BeerModel>, GenericError>
    suspend fun getBeerById(id :Int): Either<BeerModel, GenericError>
}