package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class IngredientsDTO(
    @SerializedName("malt") var malt: ArrayList<MaltDTO> = arrayListOf(),
    @SerializedName("hops") var hops: ArrayList<HopsDTO> = arrayListOf(),
    @SerializedName("yeast") var yeast: String? = null
)