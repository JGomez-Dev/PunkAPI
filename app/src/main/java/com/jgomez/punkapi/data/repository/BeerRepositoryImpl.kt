package com.jgomez.punkapi.data.repository

import com.jgomez.punkapi.core.utils.Either
import com.jgomez.punkapi.core.utils.eitherFailure
import com.jgomez.punkapi.core.utils.eitherSuccess
import com.jgomez.punkapi.core.utils.onSuccess
import com.jgomez.punkapi.data.datasource.remote.BeerApiService
import com.jgomez.punkapi.data.mapper.toDomain
import com.jgomez.punkapi.data.mapper.toDomainModel
import com.jgomez.punkapi.domain.model.BeerModel
import com.jgomez.punkapi.domain.model.GenericError
import com.jgomez.punkapi.domain.repository.BeerRepository

class BeerRepositoryImpl(private val beerApiService: BeerApiService) : BeerRepository {
    override fun getBeers(): Either<List<BeerModel>, GenericError> {
        val beerList = beerApiService.getBeers()
            .toDomainModel { beerDTOList ->
                beerDTOList.map { beerDTO ->
                    beerDTO.toDomain()
                }
            }
        if (beerList.isSuccessful) {
            beerList.data?.let {
                return eitherSuccess(beerList.data!!)
            }
        }
        return eitherFailure(GenericError("Ha ocurrido un error al recuperar los datos", beerList.code ?: 0))
    }

    override fun getBeerById(id: Int): Either<BeerModel, GenericError> {
        val beerList = getBeers()
        beerList.onSuccess { beerModel ->
            beerModel.firstOrNull { it.id == id }?.let { beer ->
                return eitherSuccess(beer)
            }
        }
        return eitherFailure(GenericError("Ha ocurrido un error al recuperar eld etalle", 0))
    }
}