package com.example.dogs.data.model

data class DogImage(
    val breeds: List<BreedX>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)