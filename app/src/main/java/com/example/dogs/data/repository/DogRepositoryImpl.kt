package com.example.dogs.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.dogs.data.api.ApiService
import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.data.model.DogImage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DogRepository {
    override suspend fun getDogs(): Pager<Int, Breed> {
        return Pager(PagingConfig(pageSize = 20)) {
            DogsPagingSource(apiService)
        }
    }

    override suspend fun getDogById(id: Int): Dog {
        return withContext(ioDispatcher) {
            apiService.getDogById(id)
        }
    }

    override suspend fun getDogImageById(id: String): DogImage {
        return withContext(ioDispatcher) {
            apiService.getDogImageById(id)
        }
    }
}