package com.jgomez.punkapi.data.dto.beer

import com.google.gson.annotations.SerializedName

data class BeerDTO(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("first_brewed") var firstBrewed: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("abv") var abv: Double? = null,
    @SerializedName("ibu") var ibu: Double? = null,
    @SerializedName("target_fg") var targetFg: Double? = null,
    @SerializedName("target_og") var targetOg: Double? = null,
    @SerializedName("ebc") var ebc: Double? = null,
    @SerializedName("srm") var srm: Double? = null,
    @SerializedName("ph") var ph: Double? = null,
    @SerializedName("attenuation_level") var attenuationLevel: Double? = null,
    @SerializedName("volume") var volume: VolumeDTO? = VolumeDTO(),
    @SerializedName("boil_volume") var boilVolume: BoilVolumeDTO? = BoilVolumeDTO(),
    @SerializedName("method") var method: MethodDTO? = MethodDTO(),
    @SerializedName("ingredients") var ingredients: IngredientsDTO? = IngredientsDTO(),
    @SerializedName("food_pairing") var foodPairing: ArrayList<String> = arrayListOf(),
    @SerializedName("brewers_tips") var brewersTips: String? = null,
    @SerializedName("contributed_by") var contributedBy: String? = null
)