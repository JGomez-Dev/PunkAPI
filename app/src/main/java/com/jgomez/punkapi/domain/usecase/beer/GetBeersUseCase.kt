package com.jgomez.punkapi.domain.usecase.beer

import com.jgomez.punkapi.core.utils.Either
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.model.GenericError
import com.jgomez.punkapi.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(private val beerRepository: BeerRepository) {
    operator fun invoke(): Flow<Either<List<BeerModel>, GenericError>> = flow {
         emit(beerRepository.getBeers())
    }
}