package com.jgomez.punkapi.data.mapper

import com.jgomez.punkapi.domain.model.ResponseModel
import retrofit2.Response

inline fun <reified T, U> Response<T>.toDomainModel(
    toDomain: (T) -> U
): ResponseModel<U> {
    return ResponseModel(
        data = this.body()?.let { toDomain(it) },
        isSuccessful = this.isSuccessful,
        code = this.code(),
        message = this.message()
    )
}
