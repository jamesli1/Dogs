package com.example.dogs.data.model

import com.google.gson.annotations.SerializedName

data class Dog(
    val id: Int,
    val name: String,
    val temperament: String? = null,
    val weight: WeightX? = null,
    val height: HeightX? = null,
    val origin: String? = null,
    @SerializedName("bred_for") val bredFor: String? = null,
    @SerializedName("breed_group") val bredGroup: String? = null,
    @SerializedName("life_span") val lifeSpan: String? = null,
    @SerializedName("reference_image_id") var referenceImageId: String? = null
)