package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class MaltDTO(
    @SerializedName("name") var name: String? = null,
    @SerializedName("amount") var amount: AmountDTO? = AmountDTO()
)
