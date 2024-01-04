package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class BoilVolumeDTO (
    @SerializedName("value" ) var value : Int?    = null,
    @SerializedName("unit"  ) var unit  : String? = null
)