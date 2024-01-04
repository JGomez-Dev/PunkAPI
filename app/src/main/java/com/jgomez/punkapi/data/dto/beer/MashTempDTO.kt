package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class MashTempDTO(
    @SerializedName("temp") var temp: TempDTO? = TempDTO(),
    @SerializedName("duration") var duration: Int? = null
)