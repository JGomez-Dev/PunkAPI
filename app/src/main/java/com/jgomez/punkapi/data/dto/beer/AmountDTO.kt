package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class AmountDTO(
    @SerializedName("value") var value: Double? = null,
    @SerializedName("unit") var unit: String? = null
)
