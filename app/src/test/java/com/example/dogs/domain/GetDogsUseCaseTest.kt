package com.example.dogs.domain

import com.example.dogs.data.repository.DogRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetDogsUseCaseTest {
    private val mockRepository: DogRepository = mockk(relaxed = true)
    private lateinit var subject: GetDogsUseCase

    @Before
    fun setUp() {
        subject = GetDogsUseCase(mockRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun invoke_getDogs() = runTest {
        subject.invoke()

        coVerify { mockRepository.getDogs() }
    }

}