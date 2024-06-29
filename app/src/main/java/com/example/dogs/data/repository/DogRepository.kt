package com.example.dogs.data.repository

import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.data.model.DogImage

interface DogRepository {

    suspend fun getDogs(): List<Breed>

    suspend fun getDogById(id: Int): Dog

    suspend fun getDogImageById(id: String): DogImage
}