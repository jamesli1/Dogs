package com.example.dogs.ui

import com.example.dogs.data.Result
import com.example.dogs.data.model.Dog
import com.example.dogs.domain.GetDogByIdUseCase
import com.example.dogs.domain.GetDogImageByIdUseCase
import com.example.dogs.domain.GetDogsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogViewModelTest {
    private val id = 1
    private val mockImageId = "ImageId"
    private val mockDog: Dog = mockk()
    private val mockGetDogsUseCase: GetDogsUseCase = mockk(relaxed = true)
    private val mockGetDogByIdUseCase: GetDogByIdUseCase = mockk(relaxed = true)
    private val mockGetDogImageByIdUseCase: GetDogImageByIdUseCase = mockk(relaxed = true)
    private lateinit var subject: DogViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        subject =
            DogViewModel(mockGetDogsUseCase, mockGetDogByIdUseCase, mockGetDogImageByIdUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun init_dogsListResponseResponse_returnLoadingResult() = runTest {
        assertEquals(Result.Loading, subject.dogsListResponse.value)
    }

    @Test
    fun loadDogs_callGetDogsUseCase() = runTest {
        subject.loadDogs()

        coVerify { mockGetDogsUseCase() }
    }

    @Test
    fun loadDog_apiError_returnFailedResponse() = runTest {
        coEvery { mockGetDogsUseCase() } throws Exception("Error")

        subject.loadDogs()

        assertEquals(Result.Error("Error"), subject.dogsListResponse.value)
    }

    @Test
    fun init_dogDetailsResponse_returnLoadingResult() = runTest {
        assertEquals(Result.Loading, subject.dogDetailsResponse.value)
    }

    @Test
    fun getDetailsById_callGetDogByIdUseCaseAndGetDogImageByIdUseCase() = runTest {
        coEvery { mockGetDogByIdUseCase(id) } returns mockDog
        every { mockDog.referenceImageId } returns mockImageId

        subject.getDetailsById(id)

        coVerify { mockGetDogByIdUseCase(id) }
        coVerify { mockGetDogImageByIdUseCase(mockImageId) }
    }

    @Test
    fun getDetailsById_api_apiError_returnFailedResponse() = runTest {
        coEvery { mockGetDogByIdUseCase(id) } throws Exception("Error")

        subject.getDetailsById(id)

        assertEquals(Result.Error("Error"), subject.dogDetailsResponse.value)
    }
}