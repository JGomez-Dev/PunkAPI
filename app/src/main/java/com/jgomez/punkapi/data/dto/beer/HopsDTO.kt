package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class HopsDTO(
    @SerializedName("name") var name: String? = null,
    @SerializedName("amount") var amount: AmountDTO? = AmountDTO(),
    @SerializedName("add") var add: String? = null,
    @SerializedName("attribute") var attribute: String? = null
)