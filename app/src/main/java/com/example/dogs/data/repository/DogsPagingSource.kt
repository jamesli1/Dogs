package com.example.dogs.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dogs.data.api.ApiService
import com.example.dogs.data.model.Breed
import javax.inject.Inject

class DogsPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Breed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getDogs(page, 20)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(
                anchorPosition
            )?.prevKey
        }
    }
}