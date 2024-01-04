package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class VolumeDTO(
    @SerializedName("value") var value: Int? = null,
    @SerializedName("unit") var unit: String? = null
)