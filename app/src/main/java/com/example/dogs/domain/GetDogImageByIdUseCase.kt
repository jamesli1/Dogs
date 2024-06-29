package com.example.dogs.domain

import com.example.dogs.data.model.DogImage
import com.example.dogs.data.repository.DogRepository
import javax.inject.Inject

class GetDogImageByIdUseCase @Inject constructor(
    private val repository: DogRepository
) {
    suspend operator fun invoke(id: String): DogImage {
        return repository.getDogImageById(id = id)
    }
}