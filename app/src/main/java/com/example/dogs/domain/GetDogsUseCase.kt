package com.example.dogs.domain

import com.example.dogs.data.model.Breed
import com.example.dogs.data.repository.DogRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): List<Breed> {
        return repository.getDogs()
    }
}