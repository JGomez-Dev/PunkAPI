package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class MethodDTO(
    @SerializedName("mash_temp") var mashTemp: ArrayList<MashTempDTO> = arrayListOf(),
    @SerializedName("fermentation") var fermentation: FermentationDTO? = FermentationDTO(),
    @SerializedName("twist") var twist: String? = null
)