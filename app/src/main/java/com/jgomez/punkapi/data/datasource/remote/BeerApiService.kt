package com.jgomez.punkapi.data.datasource.remote

import com.jgomez.punkapi.data.dto.beer.BeerDTO
import retrofit2.Response
import retrofit2.http.GET

interface BeerApiService {

    @GET("beers")
    fun getBeers() : Response<List<BeerDTO>>
}