package com.example.dogs.domain

import com.example.dogs.data.repository.DogRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class GetDogByIdUseCaseTest {
    private val id = 3
    private val mockRepository: DogRepository = mockk(relaxed = true)
    private lateinit var subject: GetDogByIdUseCase

    @Before
    fun setUp() {
        subject = GetDogByIdUseCase(mockRepository)
    }

    @Test
    fun invoke_getDogById() = runTest {
        subject.invoke(id)

        coVerify { mockRepository.getDogById(id) }
    }
}