package com.example.dogs.data.repository

import androidx.paging.Pager
import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.data.model.DogImage

interface DogRepository {

    suspend fun getDogs(): Pager<Int, Breed>

    suspend fun getDogById(id: Int): Dog

    suspend fun getDogImageById(id: String): DogImage
}