package com.example.dogs.data.repository

import com.example.dogs.data.api.ApiService
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DogRepositoryImplTest {
    private val id = 1
    private val imageId = "id"
    private val mockApiService: ApiService = mockk(relaxed = true)
    private var MockTestDispatcher = UnconfinedTestDispatcher()
    private lateinit var subject: DogRepositoryImpl

    @Before
    fun setUp() {
        subject = DogRepositoryImpl(mockApiService, MockTestDispatcher)
    }

    @Test
    fun getDogs_makeGetDogsApiCall() = runTest {
        subject.getDogs()

        coVerify { mockApiService.getDogs() }
    }

    @Test
    fun getDogById_makeGetDogByIdApiCall() = runTest {
        subject.getDogById(id)

        coVerify { mockApiService.getDogById(id) }
    }

    @Test
    fun getDogImageById_makeGetDogImageByIdApiCall() = runTest {
        subject.getDogImageById(imageId)

        coVerify { mockApiService.getDogImageById(imageId) }
    }
}