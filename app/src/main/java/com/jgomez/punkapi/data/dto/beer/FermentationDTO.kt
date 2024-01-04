package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName


data class FermentationDTO (
    @SerializedName("temp" ) var temp : TempDTO? = TempDTO()
)