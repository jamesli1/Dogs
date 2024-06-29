package com.example.dogs.data.model

import com.google.gson.annotations.SerializedName

data class Breed(
    val id: Int,
    val name: String,
    val temperament: String,
    val height: Height,
    val weight: Weight,
    @SerializedName("bred_for") val bredFor: String,
    @SerializedName("breed_group") val bredGroup: String,
    @SerializedName("country_code") val bredCode: String,
    @SerializedName("life_span") val lifeSpan: String,
    @SerializedName("reference_image_id") var referenceImageId: String
)