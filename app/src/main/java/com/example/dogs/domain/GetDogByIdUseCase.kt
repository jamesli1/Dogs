package com.example.dogs.domain

import com.example.dogs.data.model.Dog
import com.example.dogs.data.repository.DogRepository
import javax.inject.Inject

class GetDogByIdUseCase @Inject constructor(
    private val repository: DogRepository
) {
    suspend operator fun invoke(id: Int): Dog {
        return repository.getDogById(id = id)
    }
}