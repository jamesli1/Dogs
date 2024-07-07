package com.example.dogs.domain

import androidx.paging.Pager
import com.example.dogs.data.model.Breed
import com.example.dogs.data.repository.DogRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): Pager<Int, Breed> {
        return repository.getDogs()
    }
}