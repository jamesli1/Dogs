package com.example.dogs.data.api

import com.example.dogs.data.model.Breed
import com.example.dogs.data.model.Dog
import com.example.dogs.data.model.DogImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("breeds")
    suspend fun getDogs(
        @Query("page") pageNumber: Int,
        @Query("limit") perPage: Int
    ): List<Breed>

    @GET("breeds/{id}")
    suspend fun getDogById(
        @Path("id") id: Int
    ): Dog

    @GET("images/{id}")
    suspend fun getDogImageById(
        @Path("id") id: String
    ): DogImage
}