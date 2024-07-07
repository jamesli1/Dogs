package com.example.dogs.data.repository

import androidx.paging.PagingSource
import com.example.dogs.data.api.ApiService
import com.example.dogs.data.model.Breed
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DogsPagingSourceTest{
    private lateinit var subject: DogsPagingSource
    private val mockApiService: ApiService = mockk(relaxed = true)
    private val mockBreed: Breed = mockk(relaxed = true)

    @Before
    fun setUp() {
        subject = DogsPagingSource(mockApiService)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun load_onSuccess_returnsData() = runTest {
        val pagingSource = DogsPagingSource(mockApiService)
        val data = listOf(mockBreed, mockBreed, mockBreed)
        coEvery { mockApiService.getDogs(any(), any()) } returns data

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )


        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(data, page.data)
        assertEquals(null, page.prevKey)
        assertEquals(2, page.nextKey)
        coVerify { mockApiService.getDogs(1, 20) }
    }

    @Test
    fun load_onError_thrownException() = runTest {
        val pagingSource = DogsPagingSource(mockApiService)
        val exception = Exception("exception")
        coEvery { mockApiService.getDogs(any(), any()) } throws (exception)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 3,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        val error = result as PagingSource.LoadResult.Error
        assertEquals(exception, error.throwable)
        coVerify { mockApiService.getDogs(1, 20) }
    }
}