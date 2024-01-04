package com.jgomez.punkapi.domain.model

data class ResponseModel<T>(
    var data: T? = null,
    var code: Int? = null,
    var message: String? = null,
    val isSuccessful: Boolean
)
