package com.jgomez.punkapi.data.mapper

import com.jgomez.punkapi.data.dto.beer.BeerDTO
import com.jgomez.punkapi.domain.model.BeerModel

fun BeerDTO.toDomain(): BeerModel {
    return BeerModel(
        id = this.id,
        name = this.name,
        description = this.description,
        grades = this.abv,
        image = this.imageUrl
    )
}