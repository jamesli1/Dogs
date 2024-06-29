package com.example.dogs.domain

import com.example.dogs.data.repository.DogRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetDogImageByIdUseCaseTest {
    private val imageId = "id"
    private val mockRepository: DogRepository = mockk(relaxed = true)
    private lateinit var subject: GetDogImageByIdUseCase

    @Before
    fun setUp() {
        subject = GetDogImageByIdUseCase(mockRepository)
    }

    @Test
    fun invoke_getDogImageById() = runTest {
        subject.invoke(imageId)

        coVerify { mockRepository.getDogImageById(imageId) }
    }
}